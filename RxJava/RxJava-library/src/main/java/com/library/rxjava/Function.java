package com.library.rxjava;

public interface Function<T,R> {

    R apply(T t);
}