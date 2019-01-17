package com.example.tengfei.customview.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.example.tengfei.customview.observer.AbstractMenuObserver;

/**
 * @author tengfei
 */
public abstract class BaseMenuAdapter {

    private AbstractMenuObserver menuObserver;

    public void registerDataSetObserver(AbstractMenuObserver menuObserver) {
        this.menuObserver = menuObserver;
    }

    public void unregisterDataSetObserver(AbstractMenuObserver menuObserver) {
        this.menuObserver = menuObserver;
    }

    public void closeMenu() {
        if (menuObserver!=null) {
            menuObserver.closeMenu();
        }
    }

    /**
     * 继承 BaseMenuAdapter 自定义实现 getTabCount 方法来返回 tab 的个数
     *
     * @return 定义的 tab 的个数
     */
    public abstract int getTabCount();

    /**
     * 继承 BaseMenuAdapter 自定义实现 getTabView 方法来返回 tab 的布局
     *
     * @param position   当前 tab 所对应的 position
     * @param parentView 当前 tab 的父 View
     * @return 定义的 tab 的布局
     */
    public abstract View getTabView(int position, ViewGroup parentView);

    /**
     * 继承 BaseMenuAdapter 自定义实现 getMenuContentView 方法来返回 菜单内容 的布局
     *
     * @param position   当前 菜单内容 所对应的 position
     * @param parentView 当前 菜单内容 的父 View
     * @return 定义当前菜单的的布局 View 对象
     */
    public abstract View getMenuContentView(int position, ViewGroup parentView);

    /**
     * 被关闭的菜单会回调此方法
     *
     * @param view 被关闭的菜单的 TAB
     */
    public abstract void closeMenu(View view);

    /**
     * 被打开的菜单会回调此方法
     *
     * @param view 被打开的菜单的 TAB
     */
    public abstract void openMenu(View view);
}
