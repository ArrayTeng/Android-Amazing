package com.example.routerannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author tengfei
 * date 2019-10-31 23:27
 * email tengfeigo@outlook.com
 * description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Router {

    String path() default "";

    String group() default "";
}
