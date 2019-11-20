package com.example.okhttpdemo.okhttp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.internal.Util;

/**
 * @author tengfei
 * date 2019-11-20 10:52
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Dispatcher {

    private ExecutorService executorService;

    public void enqueue(RealCall.AsyncCall asyncCall) {
        // 1、将任务添加到准备执行的队列中
        asyncCall.executeOn(executorService());
    }


    ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), Util.threadFactory("OkHttp Dispatcher", false));
        }
        return executorService;
    }
}
