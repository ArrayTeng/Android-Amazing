package com.amazing.tengfei.aroutertest;

import android.app.Application;

import com.amazing.tengfei.aroutercore.EasyRouter;

/**
 * @author 飞一般的感觉
 * date 2020/5/9 11:12 AM
 * email arrayadapter.cn@gmail.com
 * description
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EasyRouter.getInstance().init(this);
    }
}
