package com.example.rxjava;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2020-03-05 22:45
 * email arrayadapter.cn@gmail.com
 * description
 */
class ObserverOnObservable<T> extends Observable<T> {

    private Observable source;
    private Schedulers scheduler;

    public ObserverOnObservable(Observable observable, Schedulers schedulers) {
        this.source = observable;
        this.scheduler = schedulers;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        source.subscribe(new ObserverOnObserver(observer,scheduler));
    }

    private class ObserverOnObserver<T> implements Observer<T>,Runnable {

        final Observer<T> observer;
        final Schedulers scheduler;
        private T value;

        public ObserverOnObserver(Observer<T> observer, Schedulers scheduler) {
            this.observer = observer;
            this.scheduler = scheduler;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(@NonNull T item) {
            value = item;
            scheduler.scheduleDirct(this);
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
