package com.example.observer.simple03;

/**
 * @author tengfei
 */
public class PeopleObserver implements Observer<Information> {
    @Override
    public void update(Information information) {
        System.out.println(" 卡哇伊 我是 "+ toString() +"  "+information.info);
    }
}
