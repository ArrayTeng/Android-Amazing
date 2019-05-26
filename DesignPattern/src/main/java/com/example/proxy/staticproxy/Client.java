package com.example.proxy.staticproxy;

/**
 * @author tengfei
 */
public class Client {

    public static void main(String[] args){
        RealSubject realSubject = new RealSubject();
        Proxy proxy = new Proxy(realSubject);
        proxy.request();
    }
}
