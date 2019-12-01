package com.example.demo.retrofit;

/**
 * @author tengfei
 * date 2019-12-01 21:30
 * email arrayadapter.cn@gmail.com
 * description
 */
public class OkHttpCall<T> implements Call<T> {
    private final ServiceMethod serviceMethod;
    private final Object[] args;

    OkHttpCall(ServiceMethod serviceMethod, Object[] args) {
        this.serviceMethod = serviceMethod;
        this.args = args;
    }

    @Override
    public void enqueue(CallBack<T> callBack) {
    }
}
