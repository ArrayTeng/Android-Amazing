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
        //使用反射的第一个步骤就是寻思着如何获取一个 Class 对象
        Class<?> clazz = context.getClass();
        //获取该对象下的所有方法
        Method[] methods = clazz.getDeclaredMethods();
        //遍历所有方法
        for (Method method : methods) {
            //获取方法上的注解
            Annotation[] annotations = method.getDeclaredAnnotations();
            //遍历所有的注解
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                //获取 EventBase 类型的注解
                EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                if (eventBase == null) {
                    continue;
                }
                //回调的方法
                String callBackMethod = eventBase.callBackMethod();
                //设置监听的方法
                String listenerSetter = eventBase.listenerSetter();
                //事件类型
                Class<?> listenerTypeClazz = eventBase.listenerType();
                //将回调的方法与Method对应
                Map<String, Method> methodMap = new HashMap<>(16);
                methodMap.put(callBackMethod, method);
                try {
                    //通过反射获取注解里的value方法
                    Method declaredMethod = annotationType.getDeclaredMethod("value");
                    int[] valueIds = (int[]) declaredMethod.invoke(annotation);
                    for (int id : valueIds) {
                        Method findViewById = clazz.getMethod("findViewById", int.class);
                        //获取到要设置监听事件的View对象
                        View view = (View) findViewById.invoke(context, id);
                        //获取View的 setOnClickListener 方法 view.getClass().getMethod([方法名],[方法的参数类型])
                        Method setOnClickListener = view.getClass().getMethod(listenerSetter, listenerTypeClazz);
                        //动态代理
                        ListenerInvocationHandler handler = new ListenerInvocationHandler(context, methodMap);
                        //返回代理对象，为保证客户端代码的透明性，代理对象和委托对象都实现了相同的接口，并且代理对象维护了委托对象的引用
                        Object proxy = Proxy.newProxyInstance(listenerTypeClazz.getClassLoader(), new Class[]{listenerTypeClazz}, handler);
                        //返回的 proxy 代理对象其实是实现了 listenerTypeClazz 的
                        //通过反射执行View的setOnClickListener方法
                        //这个代理对象可以看作是在普通开发中自定义了一个类实现了 View.OnClickListener 接口，
                        //调用到代理对象事会自执行到 InvocationHandler 的 invoke 方法
                        setOnClickListener.invoke(view, proxy);
                    }
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
