package com.example.butterknife.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tengfei
 * date 2019/6/5 10:06 AM
 * email tengfeigo@outlook.com
 * description 同于标记在方法上实现点击事件
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface OnClick {

    int value() default -1;
}
