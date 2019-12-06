package com.example.rxjavademo.rxjava;


/**
 * @author tengfei
 * date 2019-12-04 20:56
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
        @SuppressWarnings("unchecked")
        ScalarDisposable scalarDisposable = new ScalarDisposable(item, observer);
        observer.onSubscribe();
        scalarDisposable.run();
    }

    private class ScalarDisposable<T> {
        private T item;
        private Observer<T> observer;

        public ScalarDisposable(T item, Observer<T> observer) {
            this.item = item;
            this.observer = observer;
        }

        public void run() {
            try {
                observer.onNext(item);
            } catch (Exception e) {
                observer.onError(e);
            }
            observer.onComplete();
        }

    }
}
