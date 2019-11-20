package com.example.okhttpdemo.okhttp;

/**
 * @author tengfei
 * date 2019-11-20 10:55
 * email arrayadapter.cn@gmail.com
 * description
 */
public abstract class NamedRunable implements Runnable {
    @Override
    public void run() {
        execute();
    }

    abstract void execute();
}
