package com.example.rxjavademo.rxjava;

import androidx.annotation.NonNull;

import com.example.rxjavademo.R;

/**
 * @author tengfei
 * date 2019-12-04 20:46
 * email arrayadapter.cn@gmail.com
 * description
 */
public abstract class Observable<T> implements ObservableSource<T> {

    public static <T> Observable just(T item) {
        return onAssembly(new ObservableJust<T>(item));
    }

    private static <T> Observable<T> onAssembly(Observable<T> source) {
        return source;
    }

    @Override
    public void subscribe(@NonNull Observer<T> observer) {
        //Observable.just("").subscribe 这行代码最终是 ObservableJust 调用
        subscribeActual(observer);
    }

    /**
     * 如果只执行 Consumer ，Observable.just 返回的是 ObservableJust 对象，所以最终是 Observable 调用的下面的
     * subscribe(Consumer<T> consumer) 方法并执行 subscribeActual 方法，由于 ObservableJust 继承了 Observable 所以会重写
     * 抽象方法 subscribeActual ，也就是说 Observable.just("").subscribe(Consumer<T> consumer) 最终执行的是 ObservableJust 对象
     * 里重写的 subscribeActual 方法
     */
    public <T> void subscribe(Consumer<T> consumer) {
        subscribeActual(new LambdaObservable(consumer));
    }

    protected abstract void subscribeActual(Observer<T> observer);

    public <R> Observable<R> map(Function<T, R> function) {
        return onAssembly(new ObservableMap(this,function));
    }
}
