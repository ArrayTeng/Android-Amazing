package com.example.libcommon.utils

import android.util.DisplayMetrics


/**
 * @author tengfei
 * date 2020-02-07 23:58
 * email arrayadapter.cn@gmail.com
 * description
 */
fun dp2px(dpValue: Int): Int {
    val metrics: DisplayMetrics = AppGlobal.getApp()!!.resources.displayMetrics
    return (metrics.density * dpValue + 0.5f).toInt()
}

fun getScreenWidth(): Int {
    val metrics: DisplayMetrics = AppGlobal.getApp()!!.resources.displayMetrics
    return metrics.widthPixels
}

fun getScreenHeight(): Int {
    val metrics: DisplayMetrics = AppGlobal.getApp()!!.resources.displayMetrics
    return metrics.heightPixels
}