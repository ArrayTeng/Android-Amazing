package com.example.rxjavademo.rxjava;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2019-12-06 17:27
 * email arrayadapter.cn@gmail.com
 * description
 */
public class LambdaObservable<T> implements Observer<T>{

    private Consumer<T> consumer;

    public  LambdaObservable(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onNext(@NonNull T item) {
        consumer.onNext(item);
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
