package com.example.rxjava;

/**
 * 抽象观察者
 */
public interface Observer<T> {

    void onNext(T t);

    void onSubscribe();

    void onError(Throwable e);

    void onComplete();
}
