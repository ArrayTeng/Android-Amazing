package com.example.rxjava.thread;

import android.os.Handler;
import android.os.Looper;

import com.example.rxjava.ObservableSource;
import com.example.rxjava.Observer;
import com.example.rxjava.map.AbstractObservableWithUpStream;


public class ObservableObserverOn<T> extends AbstractObservableWithUpStream {

    public ObservableObserverOn(ObservableSource source) {
        super(source);
    }

    @Override
    protected void subscribeActual(Observer observer) {
        final ObserverOnObserver<T> parent = new ObserverOnObserver<T>(observer);

        source.subscribeObserver(parent);

    }

    static final class ObserverOnObserver<T> implements Observer<T>{

        final Observer<T> actual;

        private Handler handler;

        ObserverOnObserver(Observer<T> actual) {
            this.actual = actual;
            handler = new Handler(Looper.getMainLooper());
        }

        @Override
        public void onNext(T t) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    actual.onNext(t);
                }
            });
        }

        @Override
        public void onSubscribe() {
            actual.onSubscribe();
        }

        @Override
        public void onError(Throwable e) {
            actual.onError(e);
        }

        @Override
        public void onComplete() {
            actual.onComplete();
        }
    }
}
