package com.example.okhttpdemo.retrofit;

import com.google.gson.Gson;

/**
 * @author tengfei
 * date 2019-11-29 14:52
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Test {

    private final String json = null;

    @org.junit.Test
    public void gsonTest(){
        Gson gson = new Gson();
        Entity entity = gson.fromJson(json,Entity.class);
    }
}
