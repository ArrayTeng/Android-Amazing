package com.example.iterator.user;

/**
 * @author tengfei
 */
public class UserInfo {

    public String name;
    public String passWord;

    public UserInfo(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
