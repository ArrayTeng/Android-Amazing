package com.example.libcommon.utils;

import android.app.Application;

import java.lang.reflect.InvocationTargetException;

/**
 * @author tengfei
 * date 2020-02-08 12:49
 * email arrayadapter.cn@gmail.com
 * description
 */
public class AppGlobal {

    private static Application sApplication;

    public static Application getApplication() {
        if (sApplication == null) {
            try {
                sApplication = (Application) Class.forName("android.app.ActivityThread")
                        .getMethod("currentApplication")
                        .invoke(null, (Object[]) null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return sApplication;
    }
}
