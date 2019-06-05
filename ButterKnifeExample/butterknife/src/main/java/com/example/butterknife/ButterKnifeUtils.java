package com.example.butterknife;

import android.content.Context;

import java.lang.reflect.InvocationTargetException;

/**
 * @author tengfei
 * date 2019/6/5 9:46 PM
 * email tengfeigo@outlook.com
 * description
 */
public class ButterKnifeUtils {

    public static void init(Context context){
        try {
            Class<?> bindClass = Class.forName(context.getClass().getName()+"$$ViewBinder");
            bindClass.getDeclaredConstructor(context.getClass()).newInstance(context);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
