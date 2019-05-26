package com.example.observer.simple03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tengfei
 */
public class Client {

    public static void main(String[] args){
//        //定义具体被观察者对象
//        WXObservable wxObservable = new WXObservable();
//        //定义观察者对象
//        PeopleObserver peopleObserver01 = new PeopleObserver();
//        PeopleObserver peopleObserver02 = new PeopleObserver();
//        //注册
//        wxObservable.register(peopleObserver01);
//        wxObservable.register(peopleObserver02);
//        //修改数据
//        wxObservable.pushInfo(new Information("哈哈"));

//        long current = 4096;
//        long count = 26112381;
//        float value =  current * 100 / (float)count;
//        value = 100*value;
//        System.out.println(" 值为 "+value);

        Map<String,List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        map.put("hah",list);

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(map.get("hah").size()+"");
    }
}
