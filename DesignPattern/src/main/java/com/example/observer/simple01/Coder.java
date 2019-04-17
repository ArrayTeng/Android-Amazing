package com.example.observer.simple01;

import java.util.Observable;
import java.util.Observer;

/**
 * @author tengfei
 */
public class Coder implements Observer {

    private String name;

    public Coder(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Hi 我是 "+name+" 开发者技术前线更新啦 "+arg);
    }
}
