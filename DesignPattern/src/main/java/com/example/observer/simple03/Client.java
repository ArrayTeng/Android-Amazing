package com.example.observer.simple03;

/**
 * @author tengfei
 */
public class Client {

    public static void main(String[] args){
        //定义具体被观察者对象
        WXObservable wxObservable = new WXObservable();
        //定义观察者对象
        PeopleObserver peopleObserver01 = new PeopleObserver();
        PeopleObserver peopleObserver02 = new PeopleObserver();
        //注册
        wxObservable.register(peopleObserver01);
        wxObservable.register(peopleObserver02);
        //修改数据
        wxObservable.pushInfo(new Information("哈哈"));

    }
}
