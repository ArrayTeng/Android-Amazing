package com.example.utils.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author tengfei
 * date 2019/3/21 4:44 PM
 * email tengfeigo@outlook.com
 * description
 */
public class MyUtils {
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

    public static int sp2px(int sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}
