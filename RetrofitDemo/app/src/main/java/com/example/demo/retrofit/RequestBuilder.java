package com.example.demo.retrofit;

import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * @author tengfei
 * date 2019-12-03 14:22
 * email arrayadapter.cn@gmail.com
 * description
 */
class RequestBuilder {

    private String baseUrl;
    private String relativePath;
    private final Object[] args;
    private final IParameterHandler<?>[] parameterHandlers;
    private HttpUrl.Builder httpBuilder;

    public RequestBuilder(String baseUrl, String relativePath, Object[] args, IParameterHandler<?>[] parameterHandlers) {

        this.baseUrl = baseUrl;
        this.relativePath = relativePath;
        this.args = args;
        this.parameterHandlers = parameterHandlers;
    }

    public Request build() {
        httpBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl + relativePath)).newBuilder();

        int count = parameterHandlers.length;

        for (int i = 0; i < count; i++) {
            parameterHandlers[i].apply(this, args[i]);
        }

        Request request = new Request.Builder()
                //由于只是学习Retrofit源码所以只支持get请求
                .get()
                .url(httpBuilder.build())
                .build();
        return request;
    }

    public void addQuery(String key, String value) {
        httpBuilder.addQueryParameter(key, value);
    }
}
