package com.example.rxjavademo.study;

/**
 * @author tengfei
 * date 2020-03-06 10:07
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface ObservableSource<T> {

    void subscribe(Observer<T> observer);
}
