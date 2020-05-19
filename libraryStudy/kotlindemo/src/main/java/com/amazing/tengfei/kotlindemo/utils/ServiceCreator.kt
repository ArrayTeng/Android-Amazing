package com.amazing.tengfei.kotlindemo.utils

import retrofit2.Retrofit

/**
 * @author 飞一般的感觉
 * date 2020/5/17 1:39 PM
 * email arrayadapter.cn@gmail.com
 * description
 */

object ServiceCreator {
    private const val BASE_URL = "10.1.64.48"

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create() = create(T::class.java)
}