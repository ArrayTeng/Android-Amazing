package com.example.tengfei.net;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author tengfei
 * date 2019/3/2 12:47 PM
 * email tengfeigo@outlook.com
 * description
 */
public class DownloadDispatcher {

    private DownloadDispatcher() {
    }

    public static DownloadDispatcher instance() {
        return Holder.DOWNLOAD_DISPATCHER;
    }

    private static final class Holder {
        private static final DownloadDispatcher DOWNLOAD_DISPATCHER = new DownloadDispatcher();
    }

    public void startDownload(String url, DownloadCallBack downloadCallBack) {
        Call call = OkHttpManager.instance().asyncCall(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                downloadCallBack.downloadFail(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                long contentLength = response.body() != null ? response.body().contentLength() : 0;
                if (contentLength <= -1) {
                    return;
                }
                DownloadTask downloadTask = new DownloadTask.Builder()
                        .contentLength(contentLength)
                        .downloadUrl(url)
                        .callBack(downloadCallBack)
                        .build();
                downloadTask.init();
            }
        });
    }
}

