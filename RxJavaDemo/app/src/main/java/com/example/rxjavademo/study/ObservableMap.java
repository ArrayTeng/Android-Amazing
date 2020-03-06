package com.example.rxjavademo.study;


import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2020-03-06 11:13
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ObservableMap<T, R> extends Observable<R> {

    private Observable<T> source;
    private Function<T, R> map;

    public ObservableMap(Observable<T> source, Function<T, R> map) {
        this.source = source;
        this.map = map;
    }

    @Override
    protected void subscribeActual(Observer<R> observer) {
        //把下一层的 observer 也传上去，不断递归知道顶层的的被贯观察者对象
        source.subscribe(new MapObserver<T, R>(observer, map));
    }


    private static class MapObserver<T, R> implements Observer<T> {

        private Observer<R> observer;
        private Function<T, R> map;

        public MapObserver(Observer<R> observer, Function<T, R> map) {
            this.observer = observer;
            this.map = map;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(@NonNull T t) {
            try {
                R res = map.apply(t);
                //当前层的 onNext 已经执行了现在调用下一层的 observer
                observer.onNext(res);
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
