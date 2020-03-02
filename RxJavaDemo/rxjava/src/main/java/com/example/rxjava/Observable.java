package com.example.rxjava;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2020-03-02 23:03
 * email arrayadapter.cn@gmail.com
 * description 被观察者
 */
public abstract class Observable<T> implements ObservableSource<T> {

    public static <T> ObservableSource<T> just(T item) {

        return onAssembly(new ObservableJust<T>(item));
    }

    private static <T> ObservableSource<T> onAssembly(ObservableJust<T> source) {
        return source;
    }

    @Override
    public void subscribe(@NonNull Observer<? super T> observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<? super T> observer);
}
