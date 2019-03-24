package com.example.utils.view;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author tengfei
 * date 2019/3/21 3:07 PM
 * email tengfeigo@outlook.com
 * description
 */
public interface INavigation {

    void createNavigation();

    void attachNavigationParams();

    void attachParent(View navigationView, ViewGroup mParent);
}
