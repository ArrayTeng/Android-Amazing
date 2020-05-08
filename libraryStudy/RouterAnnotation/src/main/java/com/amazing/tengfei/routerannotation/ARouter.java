package com.amazing.tengfei.routerannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 飞一般的感觉
 * date 2020/5/8 12:28 AM
 * email arrayadapter.cn@gmail.com
 * description
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface ARouter {

    String path();

    String group() default "";
}
