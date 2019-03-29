package com.example.utils.view.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tengfei
 * date 2019/3/27 9:45 AM
 * email tengfeigo@outlook.com
 * description
 */
public class WrapperRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerView.Adapter mRecyclerAdapter;

    private List<View> mHeaderViews;
    private List<View> mFooterViews;

    public WrapperRecyclerAdapter(RecyclerView.Adapter mRecyclerAdapter) {
        this.mRecyclerAdapter = mRecyclerAdapter;
        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        if (position < getHeaderCount()) {
            return createFooterAndHeaderHolder(mHeaderViews.get(position));
        }
        final int adjPosition = position - getHeaderCount();
        int adapterCount = 0;
        if (mRecyclerAdapter != null) {
            adapterCount = mRecyclerAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mRecyclerAdapter.onCreateViewHolder(viewGroup, mRecyclerAdapter.getItemViewType(adjPosition));
            }
        }
        return createFooterAndHeaderHolder(mFooterViews.get(adjPosition - adapterCount));
    }

    private RecyclerView.ViewHolder createFooterAndHeaderHolder(View view) {
        return new RecyclerView.ViewHolder(view) {

        };
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (position < getHeaderCount()){
            return;
        }
        final int adjPosition = position - getHeaderCount();
        if (mRecyclerAdapter != null) {
            int adapterCount = mRecyclerAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mRecyclerAdapter.onBindViewHolder(viewHolder,adjPosition);
            }
        }
    }

    @Override
    public int getItemCount() {
        return getHeaderCount() + mRecyclerAdapter.getItemCount() + getFooterCount();
    }

    private int getHeaderCount() {
        return mHeaderViews.size();
    }

    private int getFooterCount() {
        return mFooterViews.size();
    }

    public void addHeaderView(View view) {
        if (!mHeaderViews.contains(view)) {
            mHeaderViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void addFooterView(View view) {
        if (!mFooterViews.contains(view)) {
            mFooterViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void removeHeaderView(View view) {
        mHeaderViews.remove(view);
    }

    public void removeFooterView(View view) {
        mFooterViews.remove(view);
    }


}
