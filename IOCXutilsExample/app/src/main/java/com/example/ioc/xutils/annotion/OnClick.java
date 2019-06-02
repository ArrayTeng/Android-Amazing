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
@EventBase(listenerSetter = "setOnClickListener",listenerType = View.OnClickListener.class,callBackMethod = "onClick")
public @interface OnClick {

    int[] value();
}
