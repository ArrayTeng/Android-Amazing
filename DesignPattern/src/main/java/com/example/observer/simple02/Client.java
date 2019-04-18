package com.example.observer.simple02;

/**
 * @author tengfei
 */
public class Client {

    public static void main(String[] args){
        SpecificObservable specificObservable = new SpecificObservable();

        SpecificObserver specificObserver01 = new SpecificObserver();
        //SpecificObserver specificObserver02 = new SpecificObserver();

        specificObservable.register(specificObserver01);
        //specificObservable.register(specificObserver02);

        specificObservable.pushInfo(new Information("Android 开发艺术探索"));
        specificObservable.pushInfo(new Information("自定义控件开发入门与实战"));

        //specificObservable.unRegidter(specificObserver02);
        specificObserver01.allPull(specificObservable);

    }
}
