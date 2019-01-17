package com.example.tengfei.customview.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tengfei.customview.R;

/**
 * @author tengfei
 */
public class ListScreenMenuAdapter extends BaseMenuAdapter {

    private static final String TAG = ListScreenMenuAdapter.class.getName();

    private String[] items = {"类型", "品牌", "价格", "更多"};

    @Override
    public int getTabCount() {
        return items.length;
    }

    @Override
    public View getTabView(int position, ViewGroup parentView) {
        TextView tabView = (TextView) LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_list_screen_tab, parentView, false);
        tabView.setText(items[position]);
        return tabView;
    }

    @Override
    public View getMenuContentView(final int position, ViewGroup parentView) {
        TextView contentView = (TextView) LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_list_screen_content, parentView, false);
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
                Log.i(TAG, items[position]);
            }
        });
        contentView.setText(items[position]);
        return contentView;
    }

    @Override
    public void closeMenu(View view) {
        TextView tabView = (TextView) view;
        tabView.setTextColor(Color.BLACK);
    }

    @Override
    public void openMenu(View view) {
        TextView tabView = (TextView) view;
        tabView.setTextColor(Color.BLUE);
    }

}
