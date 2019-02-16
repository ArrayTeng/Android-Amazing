package com.example.common.okhttp;


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

    public Call asyncCall(String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        return okHttpClient.newCall(request);
    }
}
