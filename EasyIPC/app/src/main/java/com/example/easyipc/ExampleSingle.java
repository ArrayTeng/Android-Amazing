package com.example.easyipc;

public class ExampleSingle implements IExampleSingle{

    private UserInfo userInfo;

    private ExampleSingle(){

    }

    public static ExampleSingle getInstance(){
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
