package com.example.ioc.xutils.proxy;

import android.content.Context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author tengfei
 * date 2019/6/2 3:24 PM
 * email tengfeigo@outlook.com
 * description
 */
public class ListenerInvocationHandler implements InvocationHandler {

    private Context mContext;
    private Map<String,Method> mMethodMap;

    public ListenerInvocationHandler(Context mContext, Map<String, Method> mMethodMap) {
        this.mContext = mContext;
        this.mMethodMap = mMethodMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        Method callBackMethod = mMethodMap.get(name);
        if (callBackMethod!=null){
            return callBackMethod.invoke(mContext,args);
        }else {
            return method.invoke(proxy,args);
        }
    }
}
