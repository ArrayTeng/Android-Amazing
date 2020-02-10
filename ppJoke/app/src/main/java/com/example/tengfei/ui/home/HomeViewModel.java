package com.example.tengfei.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PagedList;

import com.alibaba.fastjson.TypeReference;
import com.example.libnetwork.ApiResponse;
import com.example.libnetwork.ApiService;
import com.example.libnetwork.JsonCallback;
import com.example.libnetwork.Request;
import com.example.tengfei.AbsViewModel;
import com.example.tengfei.model.Feed;
import com.example.tengfei.ui.MutableDataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author tengfei
 * date 2020-02-08 21:22
 * email arrayadapter.cn@gmail.com
 * description
 */
public class HomeViewModel extends AbsViewModel<Feed> {

    private volatile boolean witchCache = true;

    private MutableLiveData<PagedList<Feed>> cacheLiveData = new MutableLiveData<>();

    private AtomicBoolean loadAfterAtomic = new AtomicBoolean(false);


    @Override
    public DataSource createDataSource() {
        return new FeedDataSource();
    }

    class FeedDataSource extends ItemKeyedDataSource<Integer, Feed> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Feed> callback) {
            //加载初始化数据的
            loadData(0, callback);
            witchCache = false;
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Feed> callback) {
            //加载分页数据的
            loadData(params.key, callback);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Feed> callback) {
            //向前加载
            callback.onResult(Collections.emptyList());
        }

        @NonNull
        @Override
        public Integer getKey(@NonNull Feed item) {
            //通过最后一条item信息来返回一个 Integer 信息，这个信息用于下一次请求
            return item.id;
        }
    }

    private void loadData(int key, ItemKeyedDataSource.LoadCallback<Feed> callback) {
        if (key > 0) {
            loadAfterAtomic.set(true);
        }
        //feeds/queryHotFeedsList
        Request request = ApiService.get("/feeds/queryHotFeedsList")
                .addParam("feedId", key)
                .addParam("feedType", null)
                .addParam("pageCount", 10)
                .addParam("userId", 0).responseType(new TypeReference<ArrayList<Feed>>() {
                }.getType());
        if (witchCache) {
            request.cacheStrategy(Request.CACHE_ONLY);
            //在读取缓存操作的时候可以开启一个新的线程这样就不会阻塞接口请求了
            request.execute(new JsonCallback<ArrayList<Feed>>() {
                @Override
                public void onCacheSuccess(ApiResponse<ArrayList<Feed>> response) {
                    MutableDataSource<Integer, Feed> dataSource = new MutableDataSource<>();
                    dataSource.data.addAll(response.body);
                    PagedList pagedList = dataSource.buildPagedList(config);
                    //子线程里用 postValue
                    cacheLiveData.postValue(pagedList);
                }
            });
        }

        try {
            Request netRequest = witchCache ? request.clone() : request;
            netRequest.cacheStrategy(key == 0 ? Request.NET_CACHE : Request.NET_ONLY);
            ApiResponse<List<Feed>> response = netRequest.execute();
            List<Feed> datas = response.body == null ? Collections.emptyList() : response.body;
            callback.onResult(datas);

            if (key > 0) {
                //通过livedata发送数据告诉UI层是否应该主动关闭上拉加载分页的动画
                getBoundaryPageData().postValue(datas.size() > 0);
                loadAfterAtomic.set(false);
            }

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Log.e("loadData", "loadData: key:" + key);
    }

    public MutableLiveData<PagedList<Feed>> getCacheLiveData() {
        return cacheLiveData;
    }

    public void loadAfter(int id, ItemKeyedDataSource.LoadCallback<Feed> callback) {
        if (loadAfterAtomic.get()) {
            callback.onResult(Collections.emptyList());
            return;
        }
        ArchTaskExecutor.getIOThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                loadData(id, callback);
            }
        });
    }
}
