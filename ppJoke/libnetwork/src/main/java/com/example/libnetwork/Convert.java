package com.example.libnetwork;

import java.lang.reflect.Type;

/**
 * @author tengfei
 * date 2020-02-03 19:10
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface Convert<T> {
    T convert(String response, Type type);
}
