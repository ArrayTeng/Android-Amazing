package com.example.demo.retrofit;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Callback;

/**
 * @author tengfei
 * date 2019-12-01 21:30
 * email arrayadapter.cn@gmail.com
 * description
 */
public class OkHttpCall<T> implements Call<T> {

    private static final String TAG = "OkHttpCall_TAG";
    private final ServiceMethod serviceMethod;
    /**
     * 接口方法上的参数
     */
    private final Object[] args;

    OkHttpCall(ServiceMethod serviceMethod, Object[] args) {
        this.serviceMethod = serviceMethod;
        this.args = args;
    }

    @Override
    public void enqueue(final CallBack<T> callBack) {
        okhttp3.Call call = serviceMethod.createCall(args);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                if (callBack != null) {
                    callBack.onFailure(OkHttpCall.this, e);
                }
            }

            @Override
            public void onResponse(@NotNull okhttp3.Call call, @NotNull okhttp3.Response response) throws IOException {
                com.example.demo.retrofit.Response rResponse = new com.example.demo.retrofit.Response();
                Log.e(TAG, "onResponse: "+response.body().string());
                rResponse.body = serviceMethod.parseBody(response.body());
                if (callBack != null) {
                    callBack.onResponse(OkHttpCall.this, rResponse);
                    System.out.println(response.body().toString());
                }


            }
        });
    }
}
