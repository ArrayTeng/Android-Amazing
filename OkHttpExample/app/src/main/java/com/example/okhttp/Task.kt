package com.example.okhttp

import android.util.Log
import java.io.File
import java.io.IOException
import java.util.concurrent.*

/**
 * @author tengfei
 * date 2019/3/5 11:28 AM
 * email tengfeigo@outlook.com
 * description 任务类，负责线程池的维护以及任务的执行
 */
class Task(contentLength: Long, url: String, downloadCallBack: DownloadCallBack) {
    private var contentLength: Long? = null
    private var downloadCallBack: DownloadCallBack? = null
    private var url: String? = null
    private var executorService: ExecutorService? = null

    init {
        this.contentLength = contentLength
        this.downloadCallBack = downloadCallBack
        this.url = url
    }


    private val cpuCount = Runtime.getRuntime().availableProcessors()
    private val threadSize = Math.max(2, Math.min(cpuCount - 1, 4))

    // 创建线程池用来维护线程
    private fun executorService(): ExecutorService {
        if (executorService == null) {
            executorService = ThreadPoolExecutor(0, threadSize, 30, TimeUnit.SECONDS,
                    SynchronousQueue<Runnable>(), object : ThreadFactory {
                override fun newThread(r: Runnable?): Thread {
                    val thread = Thread(r, "Task")
                    thread.isDaemon = false
                    return thread
                }
            })
        }
        return executorService as ExecutorService
    }


    fun operation() {
        val singleThreadLength = contentLength!! / threadSize
        for (i in 0 until threadSize) {
            val start = i * singleThreadLength
            val end = i * singleThreadLength - 1
            val downloadRunnable = DownloadRunnable(url!!, start, end, object : DownloadCallBack {
                override fun onFailure(e: IOException) {
                }

                override fun onSuccess(file: File) {
                    Log.i("tmd", "success")
                }

            })
            executorService().execute(downloadRunnable)
        }
    }

}
