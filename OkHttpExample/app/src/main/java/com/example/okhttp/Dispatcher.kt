package com.example.okhttp

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * @author tengfei
 * date 2019/3/4 10:53 PM
 * email tengfeigo@outlook.com
 * description 用于任务的调度分发
 */
class Dispatcher private constructor() {

    companion object {
        val instance = Holder.dispatcher
    }

    private object Holder {
        val dispatcher = Dispatcher()
    }

    fun startDownload(url: String, downloadCallBack: DownloadCallBack) {
        val call = OkHttpManager.instance.asyncRequest(url)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                downloadCallBack.onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val contentLength: Long = response.body()?.contentLength()!!
                if (contentLength <= -1) {
                    return
                }
                val task = Task(contentLength, url, downloadCallBack)
                task.operation()
            }
        })
    }
}