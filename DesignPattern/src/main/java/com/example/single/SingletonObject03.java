package com.example.single;

/**
 * DCL方式
 *
 * @author tengfei
 */
public class SingletonObject03 {

    private volatile static SingletonObject03 SINGLETON_OBJECT_03 = null;

    private SingletonObject03() {
    }

    /**
     * synchronized 修饰一个静态方法的时候其作用范围是整个方法，作用的对象是这个类的所有对象
     */
    public static SingletonObject03 instance() {
        //第一层判断是为了不必要的同步
        if (SINGLETON_OBJECT_03 == null) {
            //实际上，synchronized(this)以及非static的synchronized方法，只能防止多个线程同时执行同一个对象的同步代码段
            synchronized (SingletonObject03.class){
                //第二层判断是为了在对象为空的时候创建对象
                if (SINGLETON_OBJECT_03 == null){
                    SINGLETON_OBJECT_03 = new SingletonObject03();
                }
            }
        }
        return SINGLETON_OBJECT_03;
    }
}
/*
* note：需要注意的是 SingletonObject03 SINGLETON_OBJECT_03 = new SingletonObject03();并不是一个原子操作，它最终会被编译成多条汇编指令
* 1、给 SingletonObject03 实例分配内存
* 2、调用 SingletonObject03 的构造方法初始化成员字段
* 3、将 SINGLETON_OBJECT_03 指向分配的内存
*java
* volatile 关键字的作用
* 1、防止重排序
* 2、线程可见行，某一线程改了公用对象（变量），短时间内另一个线程是不可见的，因为每一个线程都有自己的缓存区（线程工作区）
*
* */