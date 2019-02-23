package com.example.common.okhttp.download;


import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Objects;

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
    private final DownloadCallBack downloadCallBack;

    private static final int STATUS_DOWNLOADING = 0x001;
    private static final int STATUS_STOP = 0x002;
    private int mStatus = STATUS_DOWNLOADING;

    private long mProgress;

    public DownloadRunnable(String mUrl, int threadId, long startSize, long endSize, DownloadCallBack downloadCallBack) {
        this.mUrl = mUrl;
        this.threadId = threadId;
        this.startSize = startSize;
        this.endSize = endSize;
        this.downloadCallBack = downloadCallBack;
    }

    @Override
    public void run() {
        //只负责读写自己的内容
        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;
        try {
            Response response = OkHttpManager.getInstance().syncResponse(mUrl, startSize, endSize);
            inputStream = Objects.requireNonNull(response.body()).byteStream();
            File file = FileManager.getInstance().getFile(mUrl);
            randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(startSize);

            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                if (mStatus == STATUS_STOP) {
                    break;
                }
                mProgress += len;
                randomAccessFile.write(buffer, 0, len);
            }
            downloadCallBack.onSuccess(file);
        } catch (IOException e) {
            downloadCallBack.onFailure(e);
        } finally {
            Utils.close(inputStream);
            Utils.close(randomAccessFile);
        }
    }

    public void stop() {
        mStatus = STATUS_STOP;
    }
}
