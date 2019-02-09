package com.example.common.okhttp.interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author tengfei
 * date 2019/2/9 12:37 PM
 * email tengfeigo@outlook.com
 * description
 */
public class CacheRequestInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        //如果在没有网的情况下只读缓存
        if (!isNetWork()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        return chain.proceed(request);
    }

    private boolean isNetWork() {
        return false;
    }
}
