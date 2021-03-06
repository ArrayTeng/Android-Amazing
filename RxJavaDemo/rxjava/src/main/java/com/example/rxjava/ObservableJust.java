package com.example.rxjava;


/**
 * @author tengfei
 * date 2020-03-02 23:35
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ObservableJust<T> extends Observable<T> {

    private T item;

    public ObservableJust(T item) {
        this.item = item;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        ScalarDisposable sd = new ScalarDisposable(observer, item);
        sd.onSubscribe();
        sd.run();
    }
}
