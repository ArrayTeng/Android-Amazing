package com.example.common.okhttp.download;

import android.util.Log;

import java.io.File;
import java.io.IOException;
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
    private long mContentLength;
    private List<DownloadRunnable> mRunables;
    private DownloadCallBack downloadCallBack;
    private volatile int mSucceedNumber;

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

    public DownloadTask(String url, long contentLength, DownloadCallBack downloadCallBack) {
        this.mUrl = url;
        this.mContentLength = contentLength;
        this.downloadCallBack = downloadCallBack;
        mRunables = new ArrayList<>();
    }

    public void init() {
        for (int i = 0; i < THREAD_SIZE; i++) {
            //计算出每一个线程要下载的内容
            long threadSize = mContentLength / THREAD_SIZE;
            long startSize = i * threadSize;
            long endSize = (i+1)* threadSize - 1;
            Log.i("tmd","mContentLength : "+mContentLength+"  threadSize : "+threadSize+" startSize : "+startSize+"  endSize : "+endSize);
            if (i == THREAD_SIZE - 1) {
                endSize = mContentLength - 1;
            }
            DownloadRunnable downloadRunnable = new DownloadRunnable(mUrl, i, startSize, endSize, new DownloadCallBack() {

                @Override
                public void onFailure(IOException e) {
                    downloadCallBack.onFailure(e);
                }

                @Override
                public void onSuccess(File file) {
                    synchronized (DownloadTask.this) {
                        mSucceedNumber += 1;
                        if (mSucceedNumber == THREAD_SIZE) {
                            downloadCallBack.onSuccess(file);
                        }
                    }

                }
            });
            executorService.execute(downloadRunnable);
        }
    }
}
