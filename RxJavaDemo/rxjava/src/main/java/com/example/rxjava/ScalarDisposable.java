package com.example.rxjava;

/**
 * @author tengfei
 * date 2020-03-03 00:05
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ScalarDisposable<T> {

    private Observer observer;
    private T item;

    public ScalarDisposable(Observer<T> observer, T item) {
        this.observer = observer;
        this.item = item;
    }

    public void onSubscribe() {
        observer.onSubscribe();
    }

    public void run() {
        try {
            observer.onNext(item);
            observer.onComplete();
        }catch (Exception e){
            observer.onError(e);
        }
    }
}
