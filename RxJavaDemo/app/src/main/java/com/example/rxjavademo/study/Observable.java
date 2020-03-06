package com.example.rxjavademo.study;


/**
 * @author tengfei
 * date 2020-03-06 10:07
 * email arrayadapter.cn@gmail.com
 * description
 */
public abstract class Observable<T> implements ObservableSource<T> {

    public static <T> Observable<T> just(T item) {
        return onAssembly(new ObservableJust<T>(item));
    }

    private static <T> Observable<T> onAssembly(Observable<T> source) {
        return source;
    }

    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    public <R> Observable<R> map(Function<T, R> map) {
        return onAssembly(new ObservableMap<T, R>(this, map));
    }


    /**
     * @param schedulers 当前只实现了 IOSchedulers 所以 schedulers 的值为 IOSchedulers
     */
    public final Observable<T> subscribeOn(Schedulers schedulers) {
        return onAssembly(new ObservableSubscribeOn<T>(this, schedulers));
    }

    public final Observable<T> observerOn(Schedulers schedulers) {
        return onAssembly(new ObserverOnObservable<>(this, schedulers));
    }

    protected abstract void subscribeActual(Observer<T> observer);

}
