package com.amazing.tengfei.librarystudy;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;



/**
 * @author tengfei
 * date 2020/5/4 9:53 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
public class LibraryStudyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);

    }
}
