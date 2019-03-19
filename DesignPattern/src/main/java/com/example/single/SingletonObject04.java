package com.example.single;


/**
 * 静态内部类实现单例
 *
 * @author tengfei
 */
public class SingletonObject04 {

    private SingletonObject04() {
    }

    public static SingletonObject04 instance() {
        return Holder.SINGLETON_OBJECT_04;
    }

    private static final class Holder {
        private static final SingletonObject04 SINGLETON_OBJECT_04 = new SingletonObject04();
    }


}