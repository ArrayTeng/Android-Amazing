package com.example.iterator.user;


import com.example.iterator.user.handler.FacadeHandler;

/**
 * @author tengfei
 */
public class Client {

    public static void main(String[] args) {

        FacadeHandler facadeHandler = new FacadeHandler();
        UserInfo userInfo = facadeHandler.checkUser("haihai", "123456");
        System.out.println(userInfo.toString());
    }
}
