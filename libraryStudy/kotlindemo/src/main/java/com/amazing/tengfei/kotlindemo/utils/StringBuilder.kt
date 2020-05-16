package com.amazing.tengfei.kotlindemo.utils

/**
 * @author 飞一般的感觉
 * date 2020/5/16 9:24 PM
 * email arrayadapter.cn@gmail.com
 * description
 */

 inline fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}

inline fun <T> T.build(block: T.()->Unit):T{
    block()
    return this
}

fun main() {
    val result = StringBuilder().build {
        append("1")
        append("2")
        append("3")
        append("4")

        append("5")
    }


    print(result)
}