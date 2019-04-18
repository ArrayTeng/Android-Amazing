package com.example.observer.simple02;

/**
 * @author tengfei
 */
public interface Observer<M,T extends Observable> {

    /**
     * 接收推送
     */
     void acceptPush(M m);

    /**
     * 主动去拉去被观察者所有的数据
     */
    void allPull(T t);
}
