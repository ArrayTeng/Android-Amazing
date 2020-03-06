package com.example.rxjava;


import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2020-03-03 14:27
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ObservableMap<T, R> extends Observable<R> {

    final Observable<T> source;
    final Function<T, R> mapper;

    public ObservableMap(Observable<T> source, Function<T, R> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    @Override
    protected void subscribeActual(Observer<R> observer) {
        //调用上一个 observable 的 subscribe 方法
        //如果只有map的话最后一个 observable 对象在调用 subscribe 方法传入 observer 对象的时候会回调当前这个函数
        //然后会调用上一个 observable 对象的 subscribe 函数并将当前的 observer 对象给包装到  MapObserver 中。依次递归到 observablejust 中
        source.subscribe(new MapObserver<T, R>(observer, mapper));
    }


    static final class MapObserver<T, R> implements Observer<T> {

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
        public void onNext(@NonNull T t) {
            try {
                R applyR = function.apply(t);
                observer.onNext(applyR);
            } catch (Exception e) {
                e.printStackTrace();
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
