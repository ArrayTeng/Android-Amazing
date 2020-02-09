package com.example.tengfei.ui.home;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;

import com.alibaba.fastjson.TypeReference;
import com.example.libnetwork.ApiResponse;
import com.example.libnetwork.ApiService;
import com.example.libnetwork.JsonCallback;
import com.example.libnetwork.Request;
import com.example.tengfei.AbsViewModel;
import com.example.tengfei.model.Feed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tengfei
 * date 2020-02-08 21:22
 * email arrayadapter.cn@gmail.com
 * description
 */
public class HomeViewModel extends AbsViewModel<Feed> {

    private volatile boolean witchCache = true;

    @Override
    public DataSource createDataSource() {
        return integerFeedItemKeyedDataSource;
    }

    private ItemKeyedDataSource<Integer,Feed> integerFeedItemKeyedDataSource = new ItemKeyedDataSource<Integer, Feed>() {
       //这些回调方法都是在子线程的
        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Feed> callback) {
            //加载初始化数据的
            loadData(0,callback);
            witchCache = false;
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Feed> callback) {
            //加载分页数据的
            loadData(params.key,callback);
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
    };

    private void loadData(int key, ItemKeyedDataSource.LoadCallback<Feed> callback) {
        //feeds/queryHotFeedsList
        Request request = ApiService.get("/feeds/queryHotFeedsList")
                .addParam("feedId",key)
                .addParam("feedType",null)
                .addParam("pageCount",10)
                .addParam("userId",0).responseType(new TypeReference<ArrayList<Feed>>(){}.getType());
        if (witchCache){
            request.cacheStrategy(Request.CACHE_ONLY);
            //在读取缓存操作的时候可以开启一个新的线程这样就不会阻塞接口请求了
            request.execute(new JsonCallback<ArrayList<Feed>>() {
                @Override
                public void onCacheSuccess(ApiResponse<ArrayList<Feed>> response) {

                }
            });
        }

        try {
            Request netRequest = witchCache?request.clone():request;
            netRequest.cacheStrategy(key == 0?Request.NET_CACHE:Request.NET_ONLY);
            ApiResponse<List<Feed>> response = netRequest.execute();
            List<Feed> datas = response.body == null?Collections.emptyList():response.body;
            callback.onResult(datas);

            if (key>0){
                getBoundaryPageData().postValue(datas.size() > 0);
            }

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }
}
