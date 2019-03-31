package com.example.iterator.user.handler;

import com.example.iterator.user.AbsUserSystemHandler;
import com.example.iterator.user.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tengfei
 */
public class QQUserHandler extends AbsUserSystemHandler {

    private List<UserInfo> userInfos = new ArrayList<>();

    public QQUserHandler() {
        userInfos.add(new UserInfo("tengfei", "123456"));
        userInfos.add(new UserInfo("WangWang", "22345"));
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
