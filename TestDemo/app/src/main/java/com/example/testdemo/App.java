package com.example.testdemo;

import android.app.Application;

/**
 * @author tengfei
 * date 2019-10-14 18:35
 * email tengfeigo@outlook.com
 * description
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
    }
}
