package com.example.common.okhttp.download;


import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

/**
 * @author tengfei
 * date 2019/2/16 9:33 PM
 * email tengfeigo@outlook.com
 * description
 */
public class DownloadRunnable implements Runnable {
    private String mUrl;
    private int threadId;
    private long startSize;
    private long endSize;

    public DownloadRunnable(String mUrl, int  threadId, long startSize, long endSize) {
        this.mUrl = mUrl;
        this.threadId = threadId;
        this.startSize = startSize;
        this.endSize = endSize;
    }

    @Override
    public void run() {
        //只负责读写自己的内容
        try {
            Response response = OkHttpManager.getInstance().syncResponse(mUrl,startSize,endSize);
            InputStream inputStream = response.body().byteStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
