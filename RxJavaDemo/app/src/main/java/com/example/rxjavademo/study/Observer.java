package com.example.rxjavademo.study;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2020-03-06 10:08
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface Observer<T> {

    void onSubscribe();

    void onNext(@NonNull T t);

    void onError(@NonNull Throwable e);

    void onComplete();
}
