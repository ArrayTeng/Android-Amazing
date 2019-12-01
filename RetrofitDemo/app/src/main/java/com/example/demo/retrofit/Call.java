package com.example.demo.retrofit;

/**
 * @author tengfei
 * date 2019-12-01 21:40
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface Call<T> {

    void enqueue(CallBack<T> callBack);
}
