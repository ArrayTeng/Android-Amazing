package com.example.utils.utils;

import android.app.Activity;

import java.util.Objects;
import java.util.Stack;

/**
 * @author tengfei
 * date 2019/3/21 9:26 AM
 * email tengfeigo@outlook.com
 * description Activity管理工具类
 */
public class ActivityManager {

    private static volatile ActivityManager mActivityManager;

    private Stack<Activity> activityStack;

    private ActivityManager() {
        activityStack = new Stack<>();
    }

    public static ActivityManager instance() {
        if (mActivityManager == null) {
            synchronized (ActivityManager.class) {
                if (mActivityManager == null) {
                    mActivityManager = new ActivityManager();
                }
            }
        }
        return mActivityManager;
    }

    /**
     * 将当前 Activity 对象添加到任务栈中
     *
     * @param activity 当前 Activity 对象
     */
    public void attach(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 移除 Activity
     */
    public void detach(Activity detachActivity) {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            Activity activity = activityStack.get(i);
            if (detachActivity == activity) {
                activityStack.remove(i);
                activity.finish();
                i--;
                size--;
            }
        }
    }

    /**
     * 根据类名来移除 Activity
     */
    public void finish(Class<? extends Activity> activityClass) {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            Activity activity = activityStack.get(i);
            if (Objects.equals(activity.getClass().getCanonicalName(), activityClass.getCanonicalName())) {
                activityStack.remove(i);
                activity.finish();
                i--;
                size--;
            }
        }
    }

    /**
     * 推出整个程序
     */
    public void exitApplication() {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            Activity activity = activityStack.get(i);
            activity.finish();
        }
    }

    /**
     * 获取当前 Activity
     */
    public Activity currentActivity() {
        return activityStack.lastElement();
    }
}
