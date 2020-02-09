package com.example.tengfei;

import android.app.Application;

import com.example.libnetwork.ApiService;

/**
 * @author tengfei
 * date 2020-02-08 22:06
 * email arrayadapter.cn@gmail.com
 * description
 */
public class PPJokeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApiService.init("http://123.56.232.18:8080/serverdemo",null);
    }
}
