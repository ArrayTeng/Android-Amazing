package com.example.common.okhttp.interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author tengfei
 * date 2019/2/9 12:37 PM
 * email tengfeigo@outlook.com
 * description
 */
public class CacheResponseInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        response.newBuilder()
                .removeHeader("Cache-Control")
                .removeHeader("Pragma")
                .addHeader("Cache-Control", "max-age=" + 30)
                .build();
        return response;
    }
}
