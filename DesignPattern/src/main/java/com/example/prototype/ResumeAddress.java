package com.example.prototype;

/**
 * @author tengfei
 */
public class ResumeAddress implements Cloneable {

    public String address;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
