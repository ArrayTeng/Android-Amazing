package com.amazing.tengfei.kotlindemo

import kotlin.reflect.KProperty

/**
 * @author 飞一般的感觉
 * date 2020/5/16 11:03 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
class Delegate {

    private var propValue: Any? = null

    operator fun getValue(myClass: User, prop: KProperty<*>): Any? {
        return propValue
    }

    operator fun setValue(myClass: User, prop: KProperty<*>, value: Any?) {
        propValue = value
    }
}