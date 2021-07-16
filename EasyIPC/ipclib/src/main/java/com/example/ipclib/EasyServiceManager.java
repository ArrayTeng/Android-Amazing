package com.example.ipclib;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * 具体的服务 - 总服务
 */
public class EasyServiceManager extends Service {

    //服务获取 实例化对象

    public static int SERVICE_GET = 1;

    //服务调用
    public static int SERVICE_INVOKE = 2;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
