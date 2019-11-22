package com.example.okhttpdemo.okhttp;

/**
 * @author tengfei
 * date 2019-11-21 12:22
 * email arrayadapter.cn@gmail.com
 * description
 */
public enum Method {

    GET("GET"),

    POST("POST");

    private String name;

    Method(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean doOutPut() {
        switch (this) {
            case POST:
                return true;
            default:
                return false;
        }
    }

}
