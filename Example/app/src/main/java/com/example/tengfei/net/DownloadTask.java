package com.example.tengfei.net;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tengfei
 * date 2019/3/2 12:52 PM
 * email tengfeigo@outlook.com
 * description
 */
public class DownloadTask {
    private long contentLength;
    private String url;
    private DownloadCallBack downloadCallBack;

    private ExecutorService executorService = executorService();
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int THREAD_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));

    private DownloadTask() {
        this(new Builder());
    }

    private DownloadTask(Builder builder) {
        this.contentLength = builder.contentLength;
        this.url = builder.url;
        this.downloadCallBack = builder.downloadCallBack;
    }

    static final class Builder {
        private long contentLength;
        private String url;
        private DownloadCallBack downloadCallBack;

        public Builder contentLength(long contentLength) {
            this.contentLength = contentLength;
            return this;
        }

        public Builder downloadUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder callBack(DownloadCallBack downloadCallBack) {
            this.downloadCallBack = downloadCallBack;
            return this;
        }

        public DownloadTask build() {
            return new DownloadTask();
        }
    }

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, THREAD_SIZE, 30,
                    TimeUnit.SECONDS, new SynchronousQueue<>(), r -> {
                Thread thread = new Thread(r, "DownloadTask");
                thread.setDaemon(false);
                return thread;
            });
        }
        return executorService;
    }

    void init() {
        long statSize = 0;
        DownloadRunnable downloadRunnable = new DownloadRunnable(url,contentLength,statSize,contentLength-1, new DownloadCallBack() {
            @Override
            public void downloadSuccess(File file) {
                downloadCallBack.downloadSuccess(file);
            }

            @Override
            public void downloadFail(IOException e) {
                downloadCallBack.downloadFail(e);
            }
        });
        executorService.execute(downloadRunnable);
    }
}
