package com.amazing.tengfei.kotlindemo.utils

import java.lang.StringBuilder

/**
 * @author 飞一般的感觉
 * date 2020/5/16 8:17 PM
 * email arrayadapter.cn@gmail.com
 * description
 */

fun String.lettersCount(): Int {
    var count = 0
    for (char in this) {
        if (char.isLetter()) {
            count++
        }
    }
    return count
}

operator fun String.times(n:Int):String{
    val builder = StringBuilder()
    repeat(n){
        builder.append(this)
    }
    return builder.toString()
}

