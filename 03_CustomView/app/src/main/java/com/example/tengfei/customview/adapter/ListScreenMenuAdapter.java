package com.example.tengfei.customview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tengfei.customview.R;

/**
 * @author tengfei
 */
public class ListScreenMenuAdapter extends BaseMenuAdapter {

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
    public View getMenuContentView(int position, ViewGroup parentView) {
        TextView contentView = (TextView) LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_list_screen_content, parentView, false);
        contentView.setText(items[position]);
        return contentView;
    }

}
