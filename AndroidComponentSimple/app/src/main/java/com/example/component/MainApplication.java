package com.example.component;

import android.app.Application;
import android.util.Log;

/**
 * @author tengfei
 * date 2019/4/10 12:27 PM
 * email tengfeigo@outlook.com
 * description
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MainApplication","init A");
        Log.i("MainApplication","init B");

    }
}
