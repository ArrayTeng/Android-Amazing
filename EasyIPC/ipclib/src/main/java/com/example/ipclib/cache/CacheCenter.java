package com.example.ipclib.cache;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class CacheCenter {

    //key 类名 value Class
    private ConcurrentHashMap<String, Class<?>> mClassMap;

    //key 类名 value 对外提供服务的方法
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Method>> mAllMethodMap;

    private CacheCenter() {
        mClassMap = new ConcurrentHashMap<>();
        mAllMethodMap = new ConcurrentHashMap<>();
    }

    public static CacheCenter getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CacheCenter INSTANCE = new CacheCenter();
    }


    public void register(Class clazz) {
        registerClass(clazz);
    }

    //保存Class
    private void registerClass(Class clazz) {
        mClassMap.put(clazz.getName(), clazz);
    }

    private void registerMethod(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            ConcurrentHashMap<String, Method> map = mAllMethodMap.get(clazz.getName());
            if (map == null){
                map = new ConcurrentHashMap<>();
                mAllMethodMap.put(clazz.getName(),map);
            }
            //获取自定义的方法签名
            //String key = getMethodParameters(method);
            //map.put(key,method);

        }
    }



}
