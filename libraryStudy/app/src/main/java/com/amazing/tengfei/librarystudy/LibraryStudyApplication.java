package com.amazing.tengfei.librarystudy;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


/**
 * @author tengfei
 * date 2020/5/4 9:53 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
public class LibraryStudyApplication extends Application {

    private boolean isDebugARouter = true;

    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        LibraryStudyApplication application = (LibraryStudyApplication)     context.getApplicationContext();
        return application.refWatcher;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebugARouter){
            ARouter.openLog();
            ARouter.openDebug();
        }

        ARouter.init(this);



        //初始话LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);


    }
}
