package com.example.okhttpdemo.okhttp;


/**
 * @author tengfei
 * date 2019-11-20 10:52
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Request {

    public String url;
    public Method method;

    public Request() {
        this(new Builder());
    }

    public Request(Builder builder){
        url = builder.url;
        method = builder.method;
    }


    public static class Builder {

        Method method;
        String url;

        public Request Build() {
            if (method == null){
                method = Method.get;
            }
            return new Request(this);
        }

        public Builder url(String url) {
            Builder.this.url = url;
            return this;
        }

        public Builder header(String key, String value) {
            return this;
        }

        public Builder get() {
            method = Method.get;
            return this;
        }


        public Builder post() {
            method = Method.post;
            return this;
        }
    }
}
