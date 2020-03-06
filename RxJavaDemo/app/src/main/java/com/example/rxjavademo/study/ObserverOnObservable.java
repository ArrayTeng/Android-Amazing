package com.example.rxjavademo.study;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2020-03-06 15:26
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ObserverOnObservable<T> extends Observable<T> {

    private Observable<T> source;
    private Schedulers schedulers;

    public ObserverOnObservable(Observable<T> source, Schedulers schedulers) {
        this.source = source;
        this.schedulers = schedulers;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        source.subscribe(new ObserverOnObserver<>(observer));
    }

    private class ObserverOnObserver<T> implements Observer<T>, Runnable {

        private Observer<T> observer;
        private T value;

        public ObserverOnObserver(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(@NonNull T t) {
            value = t;
            schedulers.scheduleDirect(this);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }

        @Override
        public void run() {
            observer.onNext(value);
        }
    }

}
