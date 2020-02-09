package com.example.libcommon.utils;

import android.util.DisplayMetrics;

/**
 * @author tengfei
 * date 2020-02-08 12:48
 * email arrayadapter.cn@gmail.com
 * description
 */
public class PixUtils {

    public static int dp2px(int dpValue) {
        DisplayMetrics metrics = AppGlobal.getApplication().getResources().getDisplayMetrics();
        return (int) (metrics.density * dpValue + 0.5f);
    }

    public static int getScreenWidth() {
        DisplayMetrics metrics = AppGlobal.getApplication().getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight() {
        DisplayMetrics metrics = AppGlobal.getApplication().getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }
}
