package com.example.rxjava;

/**
 * @author tengfei
 * date 2020-03-03 20:00
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface Consumer<T> {

    void onNext(T item);
}
