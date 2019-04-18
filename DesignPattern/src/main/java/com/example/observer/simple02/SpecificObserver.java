package com.example.observer.simple02;


/**
 * @author tengfei 具体的观察者
 */
public class SpecificObserver implements Observer<Information,SpecificObservable>{


    @Override
    public void acceptPush(Information information) {
        System.out.println(this.toString()+"   "+information.info);
    }

    @Override
    public void allPull(SpecificObservable specificObservable) {
        specificObservable.getListInfos().forEach(information -> System.out.println("拉取的消息"+"  "+information.info));
    }


}
