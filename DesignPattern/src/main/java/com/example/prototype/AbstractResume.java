package com.example.prototype;

/**
 * @author tengfei
 */
public abstract class AbstractResume implements Cloneable {

    public String age;
    public String name;
    public String sex;
    public String company;
    public String workTime;


    @Override
    protected AbstractResume clone()  {
        try {
            return (AbstractResume) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
