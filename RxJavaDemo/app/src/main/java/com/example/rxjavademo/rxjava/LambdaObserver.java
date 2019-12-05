package com.example.rxjavademo.rxjava;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2019-12-05 21:35
 * email arrayadapter.cn@gmail.com
 * description
 */
public class LambdaObserver<T> implements Observer<T> {
    Consumer<T> onNext;

    public LambdaObserver(Consumer<T> onNext) {
        this.onNext = onNext;
    }

    @Override
    public void onSubscribe() {
    }

    @Override
    public void onNext(@NonNull T item) {
        onNext.onNext(item);
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
