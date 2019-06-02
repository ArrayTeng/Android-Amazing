package com.example.ioc.xutils;

import android.content.Context;
import android.view.View;

import com.example.ioc.xutils.annotion.BindView;
import com.example.ioc.xutils.annotion.ContentView;
import com.example.ioc.xutils.annotion.EventBase;
import com.example.ioc.xutils.proxy.ListenerInvocationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
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

    public static void inJectClick(Context context) {
        Class<?> clazz = context.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        //遍历所有方法
        for (Method method : methods) {
            //获取方法上的注解
            Annotation[] annotations = method.getDeclaredAnnotations();
            //遍历所有的注解
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                if (eventBase == null) {
                    continue;
                }
                String callBackMethod = eventBase.callBackMethod();
                String listenerSetter = eventBase.listenerSetter();
                Class<?> listenerTypeClazz = eventBase.listenerType();
                Map<String,Method> methodMap = new HashMap<>();
                methodMap.put(callBackMethod,method);
                try {
                    Method declaredMethod = annotationType.getDeclaredMethod("value");
                    int[] valueIds = (int[]) declaredMethod.invoke(annotation);
                    for (int id:valueIds){
                        Method findViewById = clazz.getMethod("findViewById",int.class);
                        View view = (View) findViewById.invoke(context,id);
                        Method setOnClickListener = view.getClass().getMethod(listenerSetter,listenerTypeClazz);
                        ListenerInvocationHandler handler = new ListenerInvocationHandler(context,methodMap);
                        Object proxy = Proxy.newProxyInstance(listenerTypeClazz.getClassLoader(),new Class[]{listenerTypeClazz},handler);
                        setOnClickListener.invoke(view,proxy);
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
