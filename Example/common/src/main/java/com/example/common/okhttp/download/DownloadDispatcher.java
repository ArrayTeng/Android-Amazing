package com.example.common.okhttp.download;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author tengfei
 * date 2019/2/16 9:31 PM
 * email tengfeigo@outlook.com
 * description
 */
public class DownloadDispatcher {

    private final Deque<DownloadTask> readyTasks = new ArrayDeque<>();

    private final Deque<DownloadTask> runningTasks = new ArrayDeque<>();

    private final Deque<DownloadTask> stopTasks = new ArrayDeque<>();

    public void startDownload(String url, DownloadCallBack downloadCallBack) {
        Call call = OkHttpManager.getInstance().asyncCall(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                downloadCallBack.onFailure(call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                long contentLength = response.body().contentLength();
                if (contentLength <= -1) {
                    return;
                }

                DownloadTask downloadTask = new DownloadTask(url,contentLength );
            }
        });
    }
}
