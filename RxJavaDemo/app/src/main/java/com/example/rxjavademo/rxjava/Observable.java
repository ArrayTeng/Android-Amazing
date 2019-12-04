package com.example.rxjavademo.rxjava;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2019-12-04 20:46
 * email arrayadapter.cn@gmail.com
 * description
 */
public abstract class Observable<T> implements ObservableSource<T> {


    public static <T> ObservableSource just(T item) {
        return onAssembly(new ObservableJust<T>(item));
    }

    private static <T> ObservableSource<T> onAssembly(ObservableJust<T> source) {
        return source;
    }

    @Override
    public void subscribe(@NonNull Observer<T> observer) {
        //Observable.just("").subscribe 这行代码最终是 ObservableJust 调用
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);


}
