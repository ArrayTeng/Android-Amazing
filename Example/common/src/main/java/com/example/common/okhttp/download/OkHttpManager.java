package com.example.common.okhttp.download;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author tengfei
 * date 2019/2/15 4:01 PM
 * email tengfeigo@outlook.com
 * description
 */
public class OkHttpManager {
    private OkHttpClient okHttpClient;

    private void OkHttpClient() {
        okHttpClient = new OkHttpClient();
    }

    public static OkHttpManager getInstance() {
        return Holder.OK_HTTP_MANAGER;
    }

    private static class Holder {
        private static final OkHttpManager OK_HTTP_MANAGER = new OkHttpManager();
    }

    public Call asyncCall(String url) {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        return okHttpClient.newCall(request);
    }
}
