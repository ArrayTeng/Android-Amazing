package com.example.common.okhttp.download;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tengfei
 * date 2019/2/16 9:33 PM
 * email tengfeigo@outlook.com
 * description 该类最好设计成可以复用的类，如果是不复用的类，那么线程池对象要设计成可被回收的
 */
public class DownloadTask {
    private String mUrl;
    private long mContentLenght;
    private List<DownloadRunnable> mRunables;

    private ExecutorService executorService = executorService();

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int THREAD_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));


    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, THREAD_SIZE, 30, TimeUnit.SECONDS,
                    new SynchronousQueue<>(), runnable -> {
                Thread result = new Thread(runnable, "DownLoadTask");
                result.setDaemon(false);
                return result;
            });
        }
        return executorService;
    }

    public DownloadTask(String url, long contentLength) {
        this.mUrl = url;
        this.mContentLenght = contentLength;
        mRunables = new ArrayList<>();
    }

    public void init() {
        for (int i = 0; i < THREAD_SIZE; i++) {
            //计算出每一个线程要下载的内容
            long threadSize = mContentLenght / THREAD_SIZE;
            long startSize = i * threadSize;
            long endSize = (i + threadSize) - 1;
            if (i == THREAD_SIZE - 1) {
                endSize = mContentLenght - 1;
            }
            DownloadRunnable downloadRunable = new DownloadRunnable(mUrl,i,startSize,endSize);
            executorService.execute(downloadRunable);
        }
    }
}
