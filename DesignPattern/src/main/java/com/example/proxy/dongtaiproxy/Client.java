package com.example.proxy.dongtaiproxy;

import java.lang.reflect.Proxy;

/**
 * @author tengfei
 */
public class Client {

    public static void main(String[] args) {
        ISubject subject = new RealSubject();

        //初始化 MyInvocationHandler ，并传入委托类的实例
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(subject);
        //通过 Proxy 生成代理类
        ISubject subjectProxy = (ISubject) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                subject.getClass().getInterfaces(), myInvocationHandler);
        //通过代理类调用相关方法
        subjectProxy.commitCode("Hello World");
    }
}
