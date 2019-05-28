package com.example.proxy.dongtaiproxy;

/**
 * @author tengfei
 */
public class RealSubject implements ISubject {

    @Override
    public String commitCode(String code) {
        return code;
    }
}
