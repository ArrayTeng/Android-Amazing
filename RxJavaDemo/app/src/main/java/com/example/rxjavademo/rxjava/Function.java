package com.example.rxjavademo.rxjava;

/**
 * @author tengfei
 * date 2019-12-06 10:45
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface Function<T, R> {

    R apply(T t);
}
