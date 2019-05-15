package com.example.butterknife.simple;

import android.app.Activity;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.PortUnreachableException;
import java.security.InvalidAlgorithmParameterException;

/**
 * @author tengfei
 * date 2019/5/15 10:03 PM
 * email tengfeigo@outlook.com
 * description
 */
public class ButterKnife {

    private static final String TAG = "ButterKnife";

    @SuppressWarnings("unchecked")
    public static Unbinder bind(Activity activity){
        try {
            Class<? extends Unbinder> unBinderClassName = (Class<? extends Unbinder>) Class.forName(activity.getClass().getName()+"_ViewBinding");
            Constructor<? extends Unbinder> unBinderConstructor= unBinderClassName.getDeclaredConstructor(activity.getClass());
            Unbinder unbinder = unBinderConstructor.newInstance(activity);
            return unbinder;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            Log.i(TAG,e.getMessage());
        }
        return Unbinder.EMPTY;
    }
}
