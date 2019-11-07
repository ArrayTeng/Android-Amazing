package com.example.core;

import android.app.Activity;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author tengfei
 * date 2019-11-06 23:34
 * email tengfeigo@outlook.com
 * description
 */
public class ViewBind {

    public static void bind(Activity activity) {
        try {
            String className = activity.getClass().getName();
            Log.i("tmd",className);
            Class<?> clazz = Class.forName(className + "_ViewBind");
            Constructor constructor = clazz.getConstructor(Activity.class);
            constructor.newInstance(activity);
            clazz.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
