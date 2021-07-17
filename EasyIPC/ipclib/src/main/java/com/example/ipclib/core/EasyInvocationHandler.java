package com.example.ipclib.core;

import android.text.TextUtils;
import android.util.Log;

import com.example.ipclib.EasyServiceManager;
import com.google.gson.Gson;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EasyInvocationHandler implements InvocationHandler {

    private Class clazz;

    private static final Gson gson = new Gson();

    public EasyInvocationHandler(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        Log.e("tmd",method.getName());

        String data = EasyBinderIPC.getInstance().sendRequest(clazz,method,objects, EasyServiceManager.SERVICE_INVOKE);
        if(!TextUtils.isEmpty(data)){
            Object object = gson.fromJson(data,method.getReturnType());
            return object;
        }
        return null;
    }
}
