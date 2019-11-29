package com.example.okhttpdemo.prxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author tengfei
 * date 2019-11-26 23:14
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ProxyDemo {

    @Test
    public void runProxy() {

        Man man = new Man();

        IBank iBank = (IBank) Proxy.newProxyInstance(IBank.class.getClassLoader(), new Class<?>[]{IBank.class}, new BankInvocationHandler(man));

        iBank.makeBank();
        iBank.extraBank();

    }
}



