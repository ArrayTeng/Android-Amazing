package com.example.utils.widget.navigationbar;

import android.content.Context;
import android.view.ViewGroup;

/**
 * @author tengfei
 * date 2019/3/21 2:42 PM
 * email tengfeigo@outlook.com
 * description Android 通用导航栏
 */
public class NavigationBar extends AbsNavigationBar {

    public NavigationBar(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbstractBuilder<NavigationBar.Builder> {
        public Builder(Context mContext, ViewGroup mParent, int layoutId) {
            super(mContext, mParent, layoutId);
        }

        @Override
        public NavigationBar builder() {
            return new NavigationBar(this);
        }

    }
}
