package com.example.rxjava;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2020-03-03 20:00
 * email arrayadapter.cn@gmail.com
 * description
 */
public class LambdaObserver<T> implements Observer<T> {

    private Consumer<T> onNext;

    public LambdaObserver(Consumer<T> onNext) {
        this.onNext = onNext;
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onNext(@NonNull T t) {
        onNext.onNext(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
