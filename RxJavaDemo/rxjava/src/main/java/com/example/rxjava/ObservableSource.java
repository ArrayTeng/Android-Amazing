package com.example.rxjava;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2020-03-02 23:04
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface ObservableSource<T> {

    void subscribe(@NonNull Observer<? super T> observer);
}
