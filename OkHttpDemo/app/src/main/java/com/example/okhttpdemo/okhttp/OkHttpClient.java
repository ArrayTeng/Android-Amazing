package com.example.okhttpdemo.okhttp;

/**
 * @author tengfei
 * date 2019-11-20 10:52
 * email arrayadapter.cn@gmail.com
 * description
 */
public class OkHttpClient {

    private String interceptor;
    private String cache;
    Dispatcher dispatcher;



    public OkHttpClient(){
       this(new Builder());
    }

    private OkHttpClient(Builder builder){
        dispatcher = new Dispatcher();
        this.interceptor = builder.interceptor;
        this.cache = builder.cache;
    }

    public Call newCall(Request request){
        return RealCall.newRealCall(this);
    }

    public static class Builder {
        String interceptor;
        String cache;
        public OkHttpClient build() {
            return new OkHttpClient();
        }

        public Builder addInterceptor(String interceptor){
            this.interceptor = interceptor;
            return this;
        }

        public Builder cache(String cache){
            this.cache = cache;
            return this;
        }
    }
}
