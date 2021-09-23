package com.example.rxjava.thread;

import com.example.rxjava.ObservableSource;
import com.example.rxjava.Observer;
import com.example.rxjava.map.AbstractObservableWithUpStream;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObservableSubscribeOn<T> extends AbstractObservableWithUpStream<T, T> {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public ObservableSubscribeOn(ObservableSource<T> source) {
        super(source);
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        SubscribeOnObserver<T> subscribeOnObserver = new SubscribeOnObserver<T>(observer);
        observer.onSubscribe();
        EXECUTOR_SERVICE.submit(() -> source.subscribeObserver(subscribeOnObserver));

    }

    static final class SubscribeOnObserver<T> implements Observer<T> {

        final Observer<T> actual;

        SubscribeOnObserver(Observer<T> actual) {
            this.actual = actual;
        }

        @Override
        public void onNext(T t) {
            actual.onNext(t);
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
}
