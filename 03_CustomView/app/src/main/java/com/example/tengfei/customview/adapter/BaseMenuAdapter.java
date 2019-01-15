package com.example.tengfei.customview.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author tengfei
 */
public abstract class BaseMenuAdapter {

    public abstract int getTabCount();

    public abstract View getTabView(int position, ViewGroup parentView);

    public abstract View getMenuContentView(int position, ViewGroup parentView);

    public abstract void closeMenu(View view);

    public abstract void openMenu(View view);
}
