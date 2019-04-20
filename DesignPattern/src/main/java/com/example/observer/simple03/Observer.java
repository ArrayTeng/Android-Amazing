package com.example.observer.simple03;

/**
 * @author tengfei
 */
public interface Observer<M> {

    void update(M m);
}
