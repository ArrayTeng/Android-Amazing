package com.example.single;

/**
 * 懒汉式
 * <p>
 * 在用户第一次调用 instance 方法的时候初始化
 *
 * @author tengfei
 */
public class SingletonObject02 {

    private static SingletonObject02 SINGLETON_OBJECT_02 = null;

    private SingletonObject02() {
    }

    /**
     * synchronized 修饰一个静态方法的时候其作用范围是整个方法，作用的对象是这个类的所有对象
     */
    public static synchronized SingletonObject02 instance() {
        if (SINGLETON_OBJECT_02 == null) {
            SINGLETON_OBJECT_02 = new SingletonObject02();
        }
        return SINGLETON_OBJECT_02;
    }
}
