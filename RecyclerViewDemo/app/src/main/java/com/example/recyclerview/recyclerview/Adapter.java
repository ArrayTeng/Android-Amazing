package com.example.recyclerview.recyclerview;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author tengfei
 * date 2020-02-11 20:07
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface Adapter {

    int getCount();

    int getItemViewType(int row);

    //item类型的数量
    int getViewTypeCount();

    View getView(int position, View convertView, ViewGroup parent);

    int getHeight(int index);
}
