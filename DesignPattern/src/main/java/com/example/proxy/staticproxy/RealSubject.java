package com.example.proxy.staticproxy;

/**
 * @author tengfei
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("RealSubject request");
    }
}
