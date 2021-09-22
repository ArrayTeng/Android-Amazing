package com.example.rxjava;

/**
 * 抽象被观察者
 */
public interface ObservableSource<T> {

    void subscribeObserver(Observer<T> observer);
}
