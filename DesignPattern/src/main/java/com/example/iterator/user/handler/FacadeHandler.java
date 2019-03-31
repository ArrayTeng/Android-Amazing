package com.example.iterator.user.handler;

import com.example.iterator.user.AbsUserSystemHandler;
import com.example.iterator.user.UserInfo;

/**
 * @author tengfei
 */
public class FacadeHandler extends AbsUserSystemHandler {
    QQUserHandler qqUserHandler;

    public FacadeHandler() {

        qqUserHandler = new QQUserHandler();
        WXUserHandler wxUserHandler = new WXUserHandler();
        qqUserHandler.nextHandler(wxUserHandler);

    }

    @Override
    public UserInfo checkUser(String name, String passWord) {
        return qqUserHandler.checkUser(name, passWord);
    }
}
