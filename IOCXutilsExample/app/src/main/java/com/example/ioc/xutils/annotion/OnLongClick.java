package com.example.ioc.xutils.annotion;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tengfei
 * date 2019/6/2 1:42 PM
 * email tengfeigo@outlook.com
 * description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventBase(listenerSetter = "setOnLongClickListener",listenerType = View.OnLongClickListener.class,callBackMethod = "onLongClick")
public @interface OnLongClick {

    int[] value();
}
