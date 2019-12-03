package com.example.demo.retrofit;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import okhttp3.OkHttpClient;


/**
 * @author tengfei
 * date 2019-11-30 23:58
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Retrofit {

    private static final String TAG = "Retrofit_TAG";

    private String baseUrl;
    private Call.Factory factory;

    public String baseUrl(){
        return baseUrl;
    }


    public Call.Factory factory(){
        return this.factory;
    }

    private Map<Method,ServiceMethod> serviceMethodCacheMap = new ConcurrentHashMap<>();

    private Retrofit(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.factory = builder.factory;
    }

    public <T> T create(Class<T> service) {
        //判断 service 是不是接口


        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
                if (method.getDeclaringClass() == Object.class){
                    return method.invoke(this,args);
                }
                //ServiceMethod 负责解析接口方法里定义的注解
                ServiceMethod serviceMethod = loadServiceMethod(method);
                return new OkHttpCall(serviceMethod,args);
            }
        });
    }

    private ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod serviceMethod = serviceMethodCacheMap.get(method);
        if (serviceMethod == null){
            serviceMethod = new ServiceMethod.Builder(this,method).build();
            serviceMethodCacheMap.put(method,serviceMethod);
        }
        return serviceMethod;
    }

    public static class Builder {

        private String baseUrl;
        private Call.Factory factory;

        public Builder url(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder client(Call.Factory factory) {
            this.factory = factory;
            return this;
        }

        public Retrofit build() {
            if (null == factory) {
                factory = new OkHttpClient();
            }
            return new Retrofit(this);
        }


    }
}
