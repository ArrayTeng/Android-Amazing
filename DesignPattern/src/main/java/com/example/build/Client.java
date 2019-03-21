package com.example.build;

/**
 * 构建者设计模式：将复杂对象的构建与它的表示分离
 *
 * @author tengfei
 */
public class Client {

    public static void main(String[] args) {
        People people = new People.Builder()
                .age("11")
                .favourite("")
                .name("")
                .build();
        System.out.println(people.toString());
    }
}
