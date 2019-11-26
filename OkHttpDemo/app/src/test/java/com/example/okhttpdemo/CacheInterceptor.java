package com.example.okhttpdemo;

import android.util.Log;

/**
 * @author tengfei
 * date 2019-11-24 20:02
 * email arrayadapter.cn@gmail.com
 * description
 */
public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) {
        Request request = chain.request();
        System.out.println("intercept: CacheInterceptor");


        return chain.proceed(request);
    }
}
