package com.example.observer.simple02;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author tengfei
 */
public class Observable<T extends Observer, M> {

    private List<T> list;

    private List<M> listInfos;

    public Observable() {
        list = new ArrayList<>();
        listInfos = new ArrayList<>();
    }

    public void register(T z) {
        list.add(z);
    }

    public void unRegidter(T z) {
        list.remove(z);
    }

    public List<M> getListInfos() {
        return listInfos;
    }

    //推送信息给所有的客户
    public void pushInfo(M m) {
        listInfos.add(m);
        list.forEach(t -> {
            //noinspection unchecked
            t.acceptPush(m);
        });
    }


}
