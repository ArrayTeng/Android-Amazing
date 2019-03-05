package com.example.okhttp

import java.util.concurrent.*

/**
 * @author tengfei
 * date 2019/3/5 11:28 AM
 * email tengfeigo@outlook.com
 * description 任务类，负责线程池的维护以及任务的执行
 */
class Task(contentLength: Long, downloadCallBack: DownloadCallBack) {
    private var contentLength: Long? = null
    private var downloadCallBack: DownloadCallBack? = null

    private var executorService: ExecutorService? = null

    init {
        this.contentLength = contentLength
        this.downloadCallBack = downloadCallBack
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
            //executorService().execute()
        }
    }

}
