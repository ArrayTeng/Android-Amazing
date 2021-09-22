package com.example.rxjava;

public interface Emitter<T> {

    void onNext(T t);

    void onError(Throwable e);

    void onComplete();

}
