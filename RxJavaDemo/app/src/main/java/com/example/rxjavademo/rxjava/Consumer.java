package com.example.rxjavademo.rxjava;

/**
 * @author tengfei
 * date 2019-12-05 21:30
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface Consumer<T> {

    void onNext(T item);
}
