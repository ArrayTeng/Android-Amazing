package com.example.libnavannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author tengfei
 * date 2020-02-01 18:33
 * email arrayadapter.cn@gmail.com
 * description
 */
@Target(ElementType.TYPE)
public @interface ActivityDestination {

    String pageUrl();

    boolean isLogin() default false;

    boolean isStart() default false;
}
