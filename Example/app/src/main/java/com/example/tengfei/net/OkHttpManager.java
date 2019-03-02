package com.example.tengfei.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author tengfei
 * date 2019/3/2 12:41 PM
 * email tengfeigo@outlook.com
 * description
 */
public class OkHttpManager {

    private OkHttpClient okHttpClient;

    private OkHttpManager() {
        okHttpClient = new OkHttpClient();
    }

    public static OkHttpManager instance() {
        return Holder.OK_HTTP_MANAGER;
    }

    public Response syncResponse(String url, long startSize, long endSize) throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .addHeader("Range", "bytes=" + startSize + "-" + endSize)
                .build();
        return okHttpClient.newCall(request).execute();
    }

    private static final class Holder {
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
