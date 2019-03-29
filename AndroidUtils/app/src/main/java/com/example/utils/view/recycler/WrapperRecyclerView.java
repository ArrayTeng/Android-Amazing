package com.example.utils.view.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author tengfei
 * date 2019/3/27 11:11 AM
 * email tengfeigo@outlook.com
 * description
 */
public class WrapperRecyclerView extends RecyclerView {

    private WrapperRecyclerAdapter mAdapter;

    public WrapperRecyclerView(@NonNull Context context) {
        super(context);
    }

    public WrapperRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapperRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        mAdapter = new WrapperRecyclerAdapter(adapter);
        super.setAdapter(mAdapter);
    }


    public void addHeaderView(View view) {
        if (mAdapter != null) {
            mAdapter.addHeaderView(view);
        }
    }

    public void addFooterView(View view) {
        if (mAdapter != null) {
            mAdapter.addFooterView(view);
        }
    }

    public void removeHeaderView(View view) {
        if (mAdapter != null) {
            mAdapter.removeHeaderView(view);
        }
    }

    public void removeFooterView(View view) {
        if (mAdapter != null) {
            mAdapter.removeFooterView(view);
        }
    }
}
