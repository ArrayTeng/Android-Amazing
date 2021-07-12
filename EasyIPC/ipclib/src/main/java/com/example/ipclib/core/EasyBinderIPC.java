package com.example.ipclib.core;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;

import com.example.ipclib.EasyBinderInterface;
import com.example.ipclib.EasyServiceManager;


public class EasyBinderIPC {

    private EasyBinderIPC(){}

    public static EasyBinderIPC getInstance(){
        return Holder.INSTANCE;
    }

    private static final class Holder{
        @SuppressLint("StaticFieldLeak")
        private static final EasyBinderIPC INSTANCE = new EasyBinderIPC();
    }


    private Context mContext;

    private EasyBinderInterface easyBinderInterface;

    public void init(Context context){
        mContext = context.getApplicationContext();
    }

    public void open(Context context){
        open(context,null);
    }

    public void open(Context context,String packageName){
        init(context);
        bind(context.getApplicationContext(),packageName,EasyServiceManager.class);
    }

    private void bind(Context context, String packageName, Class<? extends EasyServiceManager> service){
        Intent intent;
        if (TextUtils.isEmpty(packageName)){
            intent = new Intent(context,service);
        }else {
            ComponentName componentName = new ComponentName(packageName,service.getName());
            intent = new Intent();
            intent.setComponent(componentName);
            intent.setAction(service.getName());
        }
        EasyServiceConnect easyServiceConnect = new EasyServiceConnect();
        context.bindService(intent,easyServiceConnect,Context.BIND_AUTO_CREATE);
    }

    private class EasyServiceConnect implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            easyBinderInterface = EasyBinderInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }



}
