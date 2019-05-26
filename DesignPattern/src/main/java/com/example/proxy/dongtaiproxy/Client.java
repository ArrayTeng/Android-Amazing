package com.example.proxy.dongtaiproxy;

import java.lang.reflect.Proxy;

/**
 * @author tengfei
 */
public class Client {

    public static void main(String[] args) {
        Subject subject = new RealSubject();

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(subject);

        Subject subjectProxy = (Subject) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                subject.getClass().getInterfaces(),myInvocationHandler);
        subjectProxy.request();
    }
}
