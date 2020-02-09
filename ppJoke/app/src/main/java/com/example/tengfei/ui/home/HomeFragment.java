package com.example.tengfei.ui.home;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libnavannotation.FragmentDestination;
import com.example.tengfei.model.Feed;
import com.example.tengfei.ui.AbsListFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * @author tengfei
 * date 2020-02-08 15:37
 * email arrayadapter.cn@gmail.com
 * description
 */
@FragmentDestination(pageUrl = "main/tabs/home" ,isStart = true)
public class HomeFragment extends AbsListFragment<Feed,HomeViewModel> {

    @Override
    protected void afterCreateView() {

    }

    @Override
    public PagedListAdapter getAdapter() {
        String feedType = getArguments() == null?"all":getArguments().getString("feedType");
        return new FeedAdapter(getContext(),feedType);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
}
