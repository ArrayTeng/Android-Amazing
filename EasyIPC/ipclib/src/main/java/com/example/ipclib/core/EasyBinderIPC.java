package com.example.ipclib.core;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;

import com.example.ipclib.ClassId;
import com.example.ipclib.EasyBinderInterface;
import com.example.ipclib.EasyServiceManager;
import com.example.ipclib.bean.RequestBean;
import com.example.ipclib.bean.RequestParameter;
import com.example.ipclib.cache.CacheCenter;
import com.google.gson.Gson;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class EasyBinderIPC {

    private EasyBinderIPC() {
    }

    public static EasyBinderIPC getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        @SuppressLint("StaticFieldLeak")
        private static final EasyBinderIPC INSTANCE = new EasyBinderIPC();
    }

    private final Gson gson = new Gson();

    private Context mContext;

    private CacheCenter cacheCenter = CacheCenter.getInstance();

    private EasyBinderInterface easyBinderInterface;

    public void init(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 开启服务
     *
     * @param context
     */
    public void open(Context context) {
        open(context, null);
    }

    public void open(Context context, String packageName) {
        init(context);
        bind(context.getApplicationContext(), packageName, EasyServiceManager.class);
    }

    /**
     * 服务注册
     */
    public void register(Class<?> clazz) {
        cacheCenter.register(clazz);
    }


    public <T> T getInstance(Class<T> clazz, Object... parameters) {
//        //通知主进程在返回给B进程
//
//        //组织请求头
//
        sendRequest(clazz, null, parameters,EasyServiceManager.SERVICE_GET);



        return getProxy(clazz);
    }

    private <T> T getProxy(Class<T> clazz) {
        ClassLoader classLoader = mContext.getClassLoader();

        return  (T)Proxy.newProxyInstance(classLoader,new Class[]{clazz},new EasyInvocationHandler(clazz));
    }

    public <T> String sendRequest(Class<T> clazz, Method method, Object[] parameters, int type) {

        String className = clazz.getAnnotation(ClassId.class).value();

        String methodName = method == null ? "getInstance" : method.getName();

        RequestParameter[] requestParameters = null;

        if (parameters != null && parameters.length > 0) {
            requestParameters = new RequestParameter[parameters.length];
            for (int i = 0;i<parameters.length;i++){
                Object parameter = parameters[i];
                //方法参数的类型
                String parameterClassName = parameter.getClass().getName();
                //参数的值，用json表示
                String parameterValue = gson.toJson(parameter);
                RequestParameter requestParameter = new RequestParameter(parameterClassName,parameterValue);
                requestParameters[i] = requestParameter;
            }
        }


        RequestBean requestBean = new RequestBean(className,methodName,requestParameters,type);

        String request = gson.toJson(requestBean);

        //执行IPC请求
        String response = null;
        try {
            response = easyBinderInterface.request(request);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return response;
    }


    /**
     * 绑定服务
     */
    private void bind(Context context, String packageName, Class<? extends EasyServiceManager> service) {
        Intent intent;
        if (TextUtils.isEmpty(packageName)) {
            intent = new Intent(context, service);
        } else {
            ComponentName componentName = new ComponentName(packageName, service.getName());
            intent = new Intent();
            intent.setComponent(componentName);
            intent.setAction(service.getName());
        }
        EasyServiceConnect easyServiceConnect = new EasyServiceConnect();
        context.bindService(intent, easyServiceConnect, Context.BIND_AUTO_CREATE);
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
