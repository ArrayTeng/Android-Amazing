package com.example.rxjava.map;

import com.example.rxjava.Observable;
import com.example.rxjava.ObservableSource;

/**
 * 装饰类
 * @param <T>
 * @param <U>
 */
public abstract class AbstractObservableWithUpStream<T,U> extends Observable<U> {

    protected final ObservableSource<T> source;

    protected AbstractObservableWithUpStream(ObservableSource<T> source) {
        this.source = source;
    }

}
