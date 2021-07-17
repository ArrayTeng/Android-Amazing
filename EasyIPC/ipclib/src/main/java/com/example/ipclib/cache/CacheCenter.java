package com.example.ipclib.cache;

import com.example.ipclib.bean.RequestBean;
import com.example.ipclib.bean.RequestParameter;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class CacheCenter {

    //key 类名 value Class
    private ConcurrentHashMap<String, Class<?>> mClassMap;

    //key 类名 value 对外提供服务的方法
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Method>> mAllMethodMap;

    private ConcurrentHashMap<String,Object> mInstanceObjectMap;

    private CacheCenter() {
        mClassMap = new ConcurrentHashMap<>();
        mAllMethodMap = new ConcurrentHashMap<>();
        mInstanceObjectMap = new ConcurrentHashMap<>();
    }

    public static CacheCenter getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CacheCenter INSTANCE = new CacheCenter();
    }


    public void putObject(String className,Object instance){
        mInstanceObjectMap.put(className,instance);
    }

    public Object getObject(String className) {
        return mInstanceObjectMap.get(className);
    }

    public void register(Class clazz) {
        registerClass(clazz);
        registerMethod(clazz);
    }

    //保存Class
    private void registerClass(Class clazz) {
        mClassMap.put(clazz.getName(), clazz);

    }

    //注册方法
    private void registerMethod(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            ConcurrentHashMap<String, Method> map = mAllMethodMap.get(clazz.getName());
            if (map == null){
                map = new ConcurrentHashMap<>();
                mAllMethodMap.put(clazz.getName(),map);
            }
            //获取自定义的方法签名
            String key = getMethodParameters(method);
            map.put(key,method);

        }
    }

    //自定义方法签名的目的是为了避免java中方法重载
    public static String getMethodParameters(Method method){
        StringBuilder result = new StringBuilder();
        result.append(method.getName());
        Class<?>[] classes = method.getParameterTypes();
        int length = classes.length;
        if(length == 0){
            return  result.toString();
        }

        for (int i = 0; i < length; ++i) {
            result.append("-").append(classes[i].getName());
        }

        return result.toString();
    }

    public static String getMethodParameters(RequestBean requestBean){
        StringBuilder result = new StringBuilder();
        result.append(requestBean.getMethodName());

        if (requestBean.getRequestParameters() == null||requestBean.getRequestParameters().length ==0){
            return  result.toString();
        }

        int length = requestBean.getRequestParameters().length;
        RequestParameter[] requestParameters = requestBean.getRequestParameters();
        for (int i = 0;i<length;i++){
            result.append("-").append(requestParameters[i].getParameterClassName());
        }

        return result.toString();
    }

    public Method getMethod(RequestBean requestBean){
        ConcurrentHashMap<String,Method> map = mAllMethodMap.get(requestBean.getClassName());
        if (map!=null){
            String key = getMethodParameters(requestBean);
            Method method = map.get(key);
            return method;
        }

        return null;
    }

    public Class<?> getClassType(String parameterClassName) {
        try {
            Class clazz = Class.forName(parameterClassName);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
