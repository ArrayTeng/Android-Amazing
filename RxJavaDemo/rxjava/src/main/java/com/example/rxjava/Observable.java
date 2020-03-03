package com.example.rxjava;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2020-03-02 23:03
 * email arrayadapter.cn@gmail.com
 * description 被观察者
 */
public abstract class Observable<T> implements ObservableSource<T> {

    public static <T> Observable<T> just(T item) {

        return onAssembly(new ObservableJust<T>(item));
    }

    //Function 泛型 第一个是原始数据类型，第二个是转换后的数据类型
    public final <R> Observable<R> map(Function<T,R> mapper){
        return onAssembly(new ObservableMap<T,R>(this,mapper));
    }

    private static <T> Observable<T> onAssembly(Observable<T> source) {
        return source;
    }

    @Override
    public void subscribe(@NonNull Observer<T> observer) {
        subscribeActual(observer);
    }

    public void subscribe(@NonNull Consumer<T> consumer){
        subscribe(new LambdaObserver<T>(consumer));
    }

    protected abstract void subscribeActual(Observer<T> observer);
}
