package com.example.tengfei.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libcommon.view.EmptyView;
import com.example.tengfei.AbsViewModel;
import com.example.tengfei.R;
import com.example.tengfei.databinding.LayoutRefreshViewBinding;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @author tengfei
 * date 2020-02-08 13:52
 * email arrayadapter.cn@gmail.com
 * description
 */
public abstract class AbsListFragment<T,M extends AbsViewModel<T>> extends Fragment implements OnRefreshListener, OnLoadMoreListener {

    private LayoutRefreshViewBinding binding;

    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private EmptyView emptyView;
    private PagedListAdapter<T, RecyclerView.ViewHolder> adapter;

    protected M mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LayoutRefreshViewBinding.inflate(inflater, container, false);

        smartRefreshLayout = binding.refreshLayout;
        recyclerView = binding.recyclerView;
        emptyView = binding.emptyView;

        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setOnRefreshListener(this);
        smartRefreshLayout.setOnLoadMoreListener(this);

        adapter = getAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAnimation(null);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.list_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        afterCreateView();
        return binding.getRoot();
    }

    protected abstract void afterCreateView();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] arguments = type.getActualTypeArguments();
        if (arguments.length>1){
            Type argument = arguments[1];
            Class modelClazz = ((Class)argument).asSubclass(AbsViewModel.class);
            mViewModel = (M) ViewModelProviders.of(this).get(modelClazz);
            mViewModel.getPageData().observe(this, new Observer<PagedList<T>>() {
                @Override
                public void onChanged(PagedList<T> pagedList) {
                    adapter.submitList(pagedList);
                }
            });

            //监听页面上是否有数据以此来设置空布局的隐藏
            mViewModel.getBoundaryPageData().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean hasData) {
                    finishRefresh(hasData);
                }
            });

        }

    }

    public void submitList(PagedList<T> pagedList) {
        if (pagedList.size() > 0) {
            adapter.submitList(pagedList);
        }

        finishRefresh(pagedList.size() > 0);
    }

    public void finishRefresh(boolean hasData) {
        PagedList<T> currentList = adapter.getCurrentList();
        hasData = hasData || currentList != null && currentList.size() > 0;
        RefreshState refreshState = smartRefreshLayout.getState();
        if (refreshState.isFooter && refreshState.isOpening) {
            smartRefreshLayout.finishLoadMore();
        } else if (refreshState.isHeader && refreshState.isOpening) {
            smartRefreshLayout.finishRefresh();
        }

        if (hasData) {
            emptyView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    public abstract PagedListAdapter getAdapter();


}
