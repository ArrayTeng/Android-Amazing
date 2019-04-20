package com.example.observer.simple03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tengfei
 */

/**
 * @param <T> 使用泛型 T 来表示观察者对象，观察者对象实现了 Observer 接口用来规范观察者对象所具备的行为，写法不是固定的，你可以自由发挥
 * @param <M> 使用泛型 M 来表示所传递的消息对象，泛型还不会的需要自行补课去了哈
 */
public class Observable<T extends Observer<M>,M> {

    private List<T> observerList;

    public Observable() {
        observerList = new ArrayList<>();
    }

    /**
     * 用一个集合来维护所有的观察者对象，注册的同时就是将观察者对象添加到集合中管理的过程
     */
    public void register(T t) {
        if (!observerList.contains(t)) {
            observerList.add(t);
        }
    }

    /**
     * 注销的过程就是将从集合中移除的过程
     */
    public void unRegister(T t) {
        if (observerList.contains(t)) {
            observerList.remove(t);
        }
    }

    /**
     * 推送消息给所有注册的观察者
     */
    public void pushInfo(M m) {
        for (T t : observerList) {
            t.update(m);
        }
    }
}
