package com.amazing.tengfei.kotlindemo.utils

import kotlin.reflect.KProperty

/**
 * @author 飞一般的感觉
 * date 2020/5/16 11:31 PM
 * email arrayadapter.cn@gmail.com
 * description
 */


class Later<T>(val block: () -> T) {

    private var value: Any? = null

    operator fun getValue(any: Any?, prop: KProperty<*>): T {
        if (value == null) {
            value = block()
        }
        return value as T
    }
}

