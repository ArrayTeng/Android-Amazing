package com.example.okhttp

import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * @author tengfei
 * date 2019/3/4 3:08 PM
 * email tengfeigo@outlook.com
 * description OkHttp管理类，同步和异步方法封装
 */
class OkHttpManager private constructor() {

    private var okHttpClient: OkHttpClient? = null

    init {
        okHttpClient = OkHttpClient()
    }

    companion object {
        val instance = Holder.okHttpManager
    }

    private object Holder {
        val okHttpManager = OkHttpManager()
    }

    fun asyncRequest(url: String): Call {
        val request = Request.Builder()
                .get()
                .url(url)
                .build()
        return okHttpClient!!.newCall(request)
    }

    fun syncRequest(url: String, start: Long, end: Long): Response {
        val request = Request.Builder()
                .get()
                .url(url)
                .addHeader("Range", "bytes=$start-$end")
                .build()
        val call = okHttpClient!!.newCall(request)
        return call.execute()
    }
}