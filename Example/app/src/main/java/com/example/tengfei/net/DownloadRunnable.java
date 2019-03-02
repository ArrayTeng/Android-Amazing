package com.example.tengfei.net;

import com.example.common.okhttp.download.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Objects;

import okhttp3.Response;

/**
 * @author tengfei
 * date 2019/3/2 1:31 PM
 * email tengfeigo@outlook.com
 * description
 */
public class DownloadRunnable implements Runnable {
    private DownloadCallBack downloadCallBack;
    private String url;
    long contentLength;
    long startSize;
    long endSize;

    public DownloadRunnable(String url,
                            long contentLength,
                            long startSize,
                            long endSize,
                            DownloadCallBack downloadCallBack) {
        this.url = url;
        this.contentLength = contentLength;
        this.startSize = startSize;
        this.endSize = endSize;
        this.downloadCallBack = downloadCallBack;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;
        try {
            Response response = OkHttpManager.instance().syncResponse(url, startSize, endSize);
            if (response.body() != null) {
                inputStream = response.body().byteStream();
            }
            File file = FileManager.instance().getFile(url);
            randomAccessFile = new RandomAccessFile(file, "rwd");
            randomAccessFile.seek(startSize);
            int len;
            byte[] buffer = new byte[1024];
            while ((len = Objects.requireNonNull(inputStream).read(buffer)) != -1) {
                randomAccessFile.write(buffer, 0, len);
            }
            downloadCallBack.downloadSuccess(file);
        } catch (IOException e) {
            e.printStackTrace();
            downloadCallBack.downloadFail(e);
        } finally {
            Utils.close(inputStream);
            Utils.close(randomAccessFile);
        }
    }
}
