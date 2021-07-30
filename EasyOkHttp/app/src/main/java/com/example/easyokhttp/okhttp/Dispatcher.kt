package com.example.easyokhttp.okhttp

import com.example.easyokhttp.okhttp.internal.AsyncCall
import com.example.easyokhttp.okhttp.internal.okHttpName
import com.example.easyokhttp.okhttp.internal.threadFactory
import java.util.concurrent.ExecutorService
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class Dispatcher constructor() {

    //同时访问服务器，不同域名最大限制64个
    @get:Synchronized
    var maxRequests = 64
        set(maxRequests) {
            synchronized(this) {
                field = maxRequests
            }
        }

    //访问同一域名服务器，最大限制5个
    @get:Synchronized
    var maxRequestsPerHost = 5
        set(maxRequestsPerHost) {
            synchronized(this) {
                field = maxRequestsPerHost
            }
        }

    private var executorServiceOrNull: ExecutorService? = null


    //创建线程池对象
    @get:Synchronized
    @get:JvmName("executorService")
    val executorService: ExecutorService
        get() {
            if (executorServiceOrNull == null) {
                executorServiceOrNull = ThreadPoolExecutor(
                    0,
                    Int.MAX_VALUE,
                    60,
                    TimeUnit.SECONDS,
                    SynchronousQueue(),
                    threadFactory("$okHttpName Dispatcher", false)
                )
            }
            return executorServiceOrNull!!
        }

    //准备执行的异步任务
    private val readyAsyncCalls = ArrayDeque<AsyncCall>()

    //正在执行的异步任务
    private val runningAsyncCalls = ArrayDeque<AsyncCall>()

    //正在执行的同步任务
    private val runningSyncCalls = ArrayDeque<AsyncCall>()


}