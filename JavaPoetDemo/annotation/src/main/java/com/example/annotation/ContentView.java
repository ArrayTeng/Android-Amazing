package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tengfei
 * date 2019-11-06 21:57
 * email tengfeigo@outlook.com
 * description
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ContentView {

    int id();
}
