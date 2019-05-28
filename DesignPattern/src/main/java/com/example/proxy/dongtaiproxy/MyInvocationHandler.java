package com.example.proxy.dongtaiproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author tengfei
 */
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 委托类对象，在构造方法中传入
     */
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 反射中的 Method 类，通过反射来找到委托类中的方法
     * @param args 委托类中需要代理的方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target, args);
        return result;
    }
}
