package com.amazing.tengfei.kotlindemo.utils

import android.content.Context
import android.content.Intent

/**
 * @author 飞一般的感觉
 * date 2020/5/17 12:51 PM
 * email arrayadapter.cn@gmail.com
 * description
 */


inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}


class SimpleData<out T>(private val data: T?) {
    fun get(): T? {
        return data
    }
}