package com.example.ioc.xutils.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tengfei
 * date 2019/6/2 12:49 PM
 * email tengfeigo@outlook.com
 * description 提供事件三要素的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface EventBase {

    /**
     * 设置监听的方法
     */
    String listenerSetter();

    /**
     * 事件类型
     */
    Class<?> listenerType();

    /**
     * 回调的方法
     */
    String callBackMethod();
}
