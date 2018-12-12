package com.example.tengfei.androidcrossprocess.blog.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author tengfei
 */
public class BinderPoolService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
