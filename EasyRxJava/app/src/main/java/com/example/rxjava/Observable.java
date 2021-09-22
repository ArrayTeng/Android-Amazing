package com.example.rxjava;

/**
 * 具体的被观察者
 */
public abstract class Observable<T> implements ObservableSource<T>{
    @Override
    public void subscribeObserver(Observer observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer observer);


    /**
     *  创建具体的被观察者,ObservableOnSubscribe包装了具体的发射器对象
     */
    public static <T> Observable create(ObservableOnSubscribe<T> source){
        return new ObservableCreate<T>(source);
    }

}
