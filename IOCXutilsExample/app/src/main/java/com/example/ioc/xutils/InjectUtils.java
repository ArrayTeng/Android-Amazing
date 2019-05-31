package com.example.ioc.xutils;

import android.content.Context;
import android.view.View;

import com.example.ioc.xutils.annotion.BindView;
import com.example.ioc.xutils.annotion.ContentView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author tengfei
 * date 2019/5/31 10:33 PM
 * email tengfeigo@outlook.com
 * description
 */
public class InjectUtils {

    public static void inJectView(Context context) {
        Class<?> mClass = context.getClass();
        Field[] fields = mClass.getDeclaredFields();
        for (Field field : fields) {
            BindView bindView = field.getAnnotation(BindView.class);
            if (bindView != null) {
                int viewId = bindView.value();
                try {
                    Method method = mClass.getMethod("findViewById", int.class);
                    View view = (View) method.invoke(context, viewId);
                    field.setAccessible(true);
                    field.set(context, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void inJectContentView(Context context) {
        int layoutId;
        Class<?> mCurrentContext = context.getClass();
        ContentView contentView = mCurrentContext.getAnnotation(ContentView.class);
        layoutId = Objects.requireNonNull(contentView).value();
        try {
            Method method = mCurrentContext.getMethod("setContentView", int.class);
            method.invoke(context, layoutId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
