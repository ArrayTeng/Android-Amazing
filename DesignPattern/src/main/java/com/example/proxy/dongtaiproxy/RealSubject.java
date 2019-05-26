package com.example.proxy.dongtaiproxy;

/**
 * @author tengfei
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("RealSubject");
    }
}
