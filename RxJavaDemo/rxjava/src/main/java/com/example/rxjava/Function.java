package com.example.rxjava;

import androidx.annotation.NonNull;

/**
 * @author tengfei
 * date 2020-03-03 14:12
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface Function<T,R> {

    R apply(@NonNull T t) throws Exception;
}
