package com.example.okhttpdemo.prxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author tengfei
 * date 2019-11-26 23:21
 * email arrayadapter.cn@gmail.com
 * description
 */
public class BankInvocationHandler implements InvocationHandler {

    Object object;

    public BankInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invokeObject = method.invoke(object, args);
        return invokeObject;
    }
}
