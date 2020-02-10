package com.example.tengfei;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;


/**
 * @author tengfei
 * date 2020-02-08 16:52
 * email arrayadapter.cn@gmail.com
 * description
 */
public abstract class AbsViewModel<T> extends ViewModel {

    private DataSource dataSource;

    private LiveData<PagedList<T>> pageData;

    private MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();

    public PagedList.Config config;

    private DataSource.Factory factory = new DataSource.Factory() {
        @NonNull
        @Override
        public DataSource create() {
            dataSource = createDataSource();
            return dataSource;
        }
    };

    //用于监听PageList上数据加载的状态
    private PagedList.BoundaryCallback<T> boundaryCallback = new PagedList.BoundaryCallback<T>() {
        //在加载初始化时候的时候返回了一个空值
        @Override
        public void onZeroItemsLoaded() {
            mutableLiveData.postValue(false);
        }

        //当列表的第一条数据被加载的时候会回调这个方法
        @Override
        public void onItemAtFrontLoaded(@NonNull T itemAtFront) {
            mutableLiveData.postValue(true);
        }

        //当pageList最后一条数据被加载到屏幕上时
        @Override
        public void onItemAtEndLoaded(@NonNull T itemAtEnd) {
            super.onItemAtEndLoaded(itemAtEnd);
        }
    };

    public AbsViewModel() {

        config = new PagedList.Config.Builder()
                //每次分页的时候需要加载的数量
                .setPageSize(10)
                //加载初始化数据，第一次加载数据的时候加载的数量
                .setInitialLoadSizeHint(12)
                //.setMaxSize() 列表一共有多少条数据，正常情况下不调用
                //.setEnablePlaceholders() 是否设置占位符
                //.setPrefetchDistance() 告诉pagedlist距离屏幕底部还有多少item时就加载下一页
                .build();

        //noinspection unchecked 创建属于 PageList 的LivaData
        pageData  = new LivePagedListBuilder(factory, config)
                //在加载初始化数据的时候需要传入的参数
                .setInitialLoadKey(0)
                //能够监听到pagedlist数据加载的状态
                .setBoundaryCallback(boundaryCallback)
                .build();
    }



    public abstract DataSource createDataSource();

    public LiveData<PagedList<T>> getPageData(){
        return pageData;
    }

    public MutableLiveData<Boolean> getBoundaryPageData() {
        return mutableLiveData;
    }

    public DataSource getDataSource(){
        return dataSource;
    }
}
