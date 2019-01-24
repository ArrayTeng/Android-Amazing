package com.example.tengfei.server;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * @author tengfei
 */
public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG,"onHandleIntent # "+Thread.currentThread().getName());
    }
}
