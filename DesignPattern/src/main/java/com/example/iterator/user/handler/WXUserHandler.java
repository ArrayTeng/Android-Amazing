package com.example.iterator.user.handler;

import com.example.iterator.user.AbsUserSystemHandler;
import com.example.iterator.user.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tengfei
 */
public class WXUserHandler extends AbsUserSystemHandler {


    private List<UserInfo> userInfos = new ArrayList<>();

    public WXUserHandler() {
        userInfos.add(new UserInfo("haihai", "123456"));
        userInfos.add(new UserInfo("daWang", "22345"));
    }

    @Override
    public UserInfo checkUser(String name, String passWord) {
        for (UserInfo userInfo : userInfos) {
            if (name.equals(userInfo.name) && passWord.equals(userInfo.passWord)) {
                return userInfo;
            }
        }
        return getNextHandler().checkUser(name, passWord);
    }


}
