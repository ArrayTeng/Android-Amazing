package com.example.rxjava.map;

import com.example.rxjava.ObservableSource;
import com.example.rxjava.Observer;

public class ObservableMap<T,U> extends AbstractObservableWithUpStream<T,U>{

    final Function<T,U> function;

    public ObservableMap(ObservableSource<T> source, Function<T, U> function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        source.subscribeObserver(new MapObserver<>(observer,function));
    }


    static final class MapObserver<T,U> extends BasicFuseableObserver<T,U>{

        final Function<T,U> function;


        protected MapObserver(Observer<U> actual, Function<T, U> function) {
            super(actual);
            this.function = function;
        }

        @Override
        public void onNext(T t) {
            U apply = function.apply(t);
            actual.onNext(apply);
        }
    }
}
