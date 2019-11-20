package com.example.okhttpdemo.okhttp;


/**
 * @author tengfei
 * date 2019-11-20 10:52
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Request {


    public static class Builder {
        public Request Build() {
            return new Request();
        }

        public Builder url(String url) {
            return this;
        }

        public Builder header(String key, String value) {
            return this;
        }
    }
}
