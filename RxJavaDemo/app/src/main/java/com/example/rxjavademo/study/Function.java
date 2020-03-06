package com.example.rxjavademo.study;

/**
 * @author tengfei
 * date 2020-03-06 11:14
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface Function<T,R> {

    R apply(T item);
}
