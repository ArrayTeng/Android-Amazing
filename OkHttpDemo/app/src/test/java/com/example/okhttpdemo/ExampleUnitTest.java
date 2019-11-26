package com.example.okhttpdemo;


import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static final String TAG = "ExampleUnitTest_TAG";


    @Test
    public void test() {

//        List<Interceptor> interceptorList = new ArrayList<>();
//        interceptorList.add(new BridgeInterceptor());
//        interceptorList.add(new CacheInterceptor());
//        interceptorList.add(new ConnectInterceptor());
//
//        Request request = new Request();
//
//        Interceptor.Chain chain = new RealInterceptorChain(interceptorList, 0, request);
//
//
//        Response response = chain.proceed(request);
//
//        System.out.println(response.toString());

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(new Cache())
                .build();
    }

}