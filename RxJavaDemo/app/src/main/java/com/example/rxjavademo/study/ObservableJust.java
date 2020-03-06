package com.example.rxjavademo.study;

/**
 * @author tengfei
 * date 2020-03-06 10:12
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
        // subscribe 函数在这里执行
        ScalarDisposable<T> scalarDisposable = new ScalarDisposable<>(item, observer);
        scalarDisposable.subscribe();
        scalarDisposable.run();
    }


    static final class ScalarDisposable<T> {

        private T item;
        private Observer<T> observer;

        public ScalarDisposable(T item, Observer<T> observer) {
            this.item = item;
            this.observer = observer;
        }

        public void subscribe() {
            observer.onSubscribe();
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
