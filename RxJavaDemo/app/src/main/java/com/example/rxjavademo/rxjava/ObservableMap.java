package com.example.rxjavademo.rxjava;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2019-12-07 00:18
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ObservableMap<T, R> extends Observable<R> {

    private Observable<T> source;
    private Function<T, R> function;

    public ObservableMap(Observable<T> source, Function<T, R> function) {
        this.source = source;
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<R> observer) {
        source.subscribe(new MapObserver<T>(observer, function));
    }


    private class MapObserver<T> implements Observer<T> {

        private Observer<R> observer;
        private Function<T, R> function;

        public MapObserver(Observer<R> observer, Function<T, R> function) {
            this.observer = observer;
            this.function = function;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(@NonNull T item) {
            try {
                R applyR = function.apply(item);
                observer.onNext(applyR);
            } catch (Exception e) {
                observer.onError(e);
            }

        }

        @Override
        public void onError(@NonNull Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }
}
