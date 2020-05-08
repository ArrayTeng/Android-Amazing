package com.amazing.tengfei.librarystudy;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.leakcanary.LeakCanary;



/**
 * @author tengfei
 * date 2020/5/4 9:53 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
public class LibraryStudyApplication extends Application {

    private boolean isDebugARouter = true;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebugARouter){
            ARouter.openLog();
            ARouter.openDebug();
        }

        ARouter.init(this);

        LeakCanary.install(this);

    }
}
