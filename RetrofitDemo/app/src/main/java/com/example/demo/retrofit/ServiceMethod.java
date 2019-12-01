package com.example.demo.retrofit;

import com.example.demo.retrofit.annotation.GET;
import com.example.demo.retrofit.annotation.POST;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author tengfei
 * date 2019-12-01 21:24
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ServiceMethod {

    private final Retrofit retrofit;
    private final Method method;


    private ServiceMethod(Builder builder) {
        this.retrofit = builder.retrofit;
        this.method = builder.method;
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
                parseAnnotation(annotation);
            }
            //获取定义在方法参数上的注解
            methodParamAnnotations = method.getParameterAnnotations();
            parameterHandlers = new IParameterHandler[methodParamAnnotations.length];
            for (Annotation[] methodParamAnnotation : methodParamAnnotations) {
                for (int i = 0; i < methodParamAnnotation.length; i++) {
                    Annotation annotation = methodParamAnnotation[i];
                    //根据参数注解选择不同的策略
                    if (annotation instanceof GET) {
                        parameterHandlers[i] = new IParameterHandler.Query<>(((GET) annotation).value());
                    }
                }
            }
            return new ServiceMethod(this);
        }

        private void parseAnnotation(Annotation annotation) {
            if (annotation instanceof GET) {
                parseMethodAndRelativePath("GET", ((GET) annotation).value());
            } else if (annotation instanceof POST) {
                parseMethodAndRelativePath("POST", ((POST) annotation).value());
            }
        }

        private void parseMethodAndRelativePath(String httpMethod, String path) {
            this.httpMethod = httpMethod;
            this.relativePath = path;
        }
    }
}
