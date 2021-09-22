package com.example.rxjava;

import com.example.rxjava.map.Function;
import com.example.rxjava.map.ObservableMap;
import com.example.rxjava.thread.ObservableObserverOn;

/**
 * 具体的被观察者
 */
public abstract class Observable<T> implements ObservableSource<T>{
    @Override
    public void subscribeObserver(Observer<T> observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);


    /**
     *  创建具体的被观察者,ObservableOnSubscribe包装了具体的发射器对象
     */
    public static <T> Observable<T> create(ObservableOnSubscribe<T> source){
        return new ObservableCreate<>(source);
    }

    public <U> Observable<U> map(Function<T,U> function){
        return new ObservableMap<>(this,function);
    }

    public final Observable<T> observerOn(){
        return new ObservableObserverOn<T>(this);
    }

}
