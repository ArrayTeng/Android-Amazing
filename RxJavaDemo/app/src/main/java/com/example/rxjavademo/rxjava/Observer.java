package com.example.rxjavademo.rxjava;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2019-12-04 20:52
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface Observer<T> {
    void onSubscribe();

    void onNext(@NonNull T item);

    void onError(@NonNull Throwable e);

    void onComplete();
}