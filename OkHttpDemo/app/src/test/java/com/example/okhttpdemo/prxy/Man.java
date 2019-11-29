package com.example.okhttpdemo.prxy;

/**
 * @author tengfei
 * date 2019-11-26 23:18
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Man implements IBank {
    @Override
    public void makeBank() {
        System.out.println("makeBank");
    }

    @Override
    public void extraBank() {
        System.out.println("extraBank");
    }
}
