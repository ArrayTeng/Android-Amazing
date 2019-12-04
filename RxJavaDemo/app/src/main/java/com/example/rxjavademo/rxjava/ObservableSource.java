package com.example.rxjavademo.rxjava;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2019-12-04 20:51
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface ObservableSource<T> {

    void subscribe(@NonNull Observer<T> observer);

}
