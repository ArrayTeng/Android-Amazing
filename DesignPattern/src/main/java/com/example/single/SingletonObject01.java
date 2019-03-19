package com.example.single;

/**
 * 饿汉式
 * 在类申明的时候就已经被初始化
 * @author tengfei
 */
public class SingletonObject01 {

    private static final SingletonObject01 SINGLETON_OBJECT_01 = new SingletonObject01();

    private SingletonObject01(){}

    public static SingletonObject01 instance(){
        return SINGLETON_OBJECT_01;
    }
}
