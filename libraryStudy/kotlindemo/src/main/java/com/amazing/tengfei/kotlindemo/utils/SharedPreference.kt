package com.amazing.tengfei.kotlindemo.utils

import android.content.SharedPreferences

/**
 * @author 飞一般的感觉
 * date 2020/5/16 9:53 PM
 * email arrayadapter.cn@gmail.com
 * description
 */

inline fun SharedPreferences.open(block:SharedPreferences.Editor.() -> Unit){
    val edit = edit()
    edit.block()
    edit.apply()
}