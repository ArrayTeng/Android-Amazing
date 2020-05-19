package com.amazing.tengfei.kotlindemo.utils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.suspendCoroutine

/**
 * @author 飞一般的感觉
 * date 2020/5/17 3:07 PM
 * email arrayadapter.cn@gmail.com
 * description
 */

suspend fun <T> Call<T>.await(): T {

    Result
    return suspendCoroutine {
        enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
            }
        })
    }
}