package com.example.okhttpdemo.okhttp;

/**
 * @author tengfei
 * date 2019-11-20 10:57
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface Call {

    void enqueue(CallBack responseCallBack);
}
