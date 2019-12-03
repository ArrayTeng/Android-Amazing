package com.example.demo.retrofit;

import com.example.demo.retrofit.annotation.GET;
import com.example.demo.retrofit.annotation.POST;
import com.example.demo.retrofit.annotation.Query;
import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * @author tengfei
 * date 2019-12-01 21:24
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ServiceMethod {

    private final Retrofit retrofit;
    private final Method method;
    String httpMethod;
    String relativePath;

    Annotation[] annotations;

    Annotation[][] methodParamAnnotations;

    IParameterHandler<?>[] parameterHandlers;

    private ServiceMethod(Builder builder) {
        this.retrofit = builder.retrofit;
        this.method = builder.method;
        this.httpMethod = builder.httpMethod;
        this.relativePath = builder.relativePath;
        this.annotations = builder.annotations;
        this.methodParamAnnotations = builder.methodParamAnnotations;
        this.parameterHandlers = builder.parameterHandlers;
    }

    public Call createCall(Object[] args) {
        Call.Factory factory = retrofit.factory();
        Request request = new RequestBuilder(retrofit.baseUrl(), relativePath, args, parameterHandlers).build();
        return factory.newCall(request);
    }

    public <T> T parseBody(ResponseBody responseBody) {
//        Type returnType = method.getGenericReturnType();
//        Class<T> dataClass = (Class<T>) ((ParameterizedType) returnType).getActualTypeArguments()[0];
//        Gson gson = new Gson();
//        T body = gson.fromJson(responseBody.charStream(), dataClass);
        return null;
    }

    static class Builder {

        Retrofit retrofit;
        Method method;

        String httpMethod;
        String relativePath;

        Annotation[] annotations;

        Annotation[][] methodParamAnnotations;

        IParameterHandler<?>[] parameterHandlers;

        Builder(Retrofit retrofit, Method method) {
            this.retrofit = retrofit;
            this.method = method;
        }

        ServiceMethod build() {
            //获取定义在方法上的所有的注解
            annotations = method.getAnnotations();

            for (Annotation annotation : annotations) {
                parseMethodAnnotation(annotation);
            }
            //获取定义在方法参数上的注解
            methodParamAnnotations = method.getParameterAnnotations();
            parameterHandlers = new IParameterHandler[methodParamAnnotations.length];

            //获取定义在参数上的注解
            int count = parameterHandlers.length;
            for (int i = 0; i < count; i++) {
                Annotation annotation = methodParamAnnotations[i][0];
                if (annotation instanceof Query) {
                    parameterHandlers[i] = new IParameterHandler.Query<>(((Query) annotation).value());
                }
            }
            return new ServiceMethod(this);
        }

        private void parseMethodAnnotation(Annotation annotation) {
            if (annotation instanceof GET) {
                parseMethodAndRelativePath("GET", ((GET) annotation).value());
            } else if (annotation instanceof POST) {
                parseMethodAndRelativePath("POST", ((POST) annotation).value());
            }
        }

        private void parseMethodAndRelativePath(String httpMethod, String path) {
            // 网络请求方法
            this.httpMethod = httpMethod;
            // 网络请求绝对路径
            this.relativePath = path;
        }
    }
}
