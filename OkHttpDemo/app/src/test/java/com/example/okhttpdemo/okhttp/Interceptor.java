package com.example.okhttpdemo.okhttp;


/**
 * @author tengfei
 * date 2019-11-24 19:27
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface Interceptor {


    Response intercept(Chain chain);


    interface Chain {

        Request request();

        Response proceed(Request request);

    }
}
