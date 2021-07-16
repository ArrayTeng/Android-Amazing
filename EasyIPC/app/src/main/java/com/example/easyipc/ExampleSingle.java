package com.example.easyipc;

import android.util.Log;

public class ExampleSingle implements IExampleSingle{

    private UserInfo userInfo;

    private ExampleSingle(){

    }

    public static ExampleSingle getInstance(String name ,Integer age){
        Log.e("tmd","服务调用成功    "+name + age);
        return Holder.INSTANCE;
    }


    private static final class Holder{
        private static final ExampleSingle INSTANCE = new ExampleSingle();
    }

    @Override
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
