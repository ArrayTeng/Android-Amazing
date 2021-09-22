package com.example.rxjava.map;

import com.example.rxjava.Observer;

public abstract class BasicFuseableObserver<T,U> implements Observer<T> {

    protected final Observer<U> actual;

    protected BasicFuseableObserver(Observer<U> actual) {
        this.actual = actual;
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
