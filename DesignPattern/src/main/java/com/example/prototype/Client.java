package com.example.prototype;

/**
 * 原型设计模式:通过原型实例指定创建对象的种类，并通过拷贝这些这些原型创建新的对象；
 * 原型模式其实就是从一个对象在创建另外一个可定制的对象，而且不需要知道任何创建的细节
 * @author tengfei
 */
public class Client {

    public static void main(String[] args)  {
        ResumeEntity resumeEntity01 =new ResumeEntity();
        resumeEntity01.name="TenFei";
        resumeEntity01.age = "21";
        resumeEntity01.resumeAddress = new ResumeAddress();
        resumeEntity01.resumeAddress.address = "上海";

        try {
            ResumeEntity resumeEntity02 = resumeEntity01.clone();
            resumeEntity02.name="feifei";
            resumeEntity02.age="23";
            resumeEntity02.resumeAddress.address="苏州";
            System.out.println("resumeEntity01 : "+resumeEntity01.toString());
            System.out.println("resumeEntity02 : "+resumeEntity02.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }
}
