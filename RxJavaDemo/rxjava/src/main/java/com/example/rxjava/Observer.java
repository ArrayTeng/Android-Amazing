package com.example.rxjava;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2020-03-02 23:28
 * email arrayadapter.cn@gmail.com
 * description 观察者
 */
public interface Observer<T> {

    void onSubscribe();

    void onNext(@NonNull T t);

    void onError(@NonNull Throwable e);

    void onComplete();
}
