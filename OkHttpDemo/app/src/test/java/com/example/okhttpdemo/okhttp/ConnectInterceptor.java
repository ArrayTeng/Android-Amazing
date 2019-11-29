package com.example.okhttpdemo.okhttp;

/**
 * @author tengfei
 * date 2019-11-24 20:02
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ConnectInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) {
        Request request = chain.request();
        System.out.println("intercept: ConnectInterceptor");
        Response response = new Response();
        response.response = "结果";
        return response;
    }
}
