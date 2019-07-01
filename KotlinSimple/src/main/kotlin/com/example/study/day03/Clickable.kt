package com.example.study.day03

/**
 * @author tengfei
 */
interface Clickable {

    fun click()

    //kotlin中的接口还可以有一个默认的实现

    fun showOff() = println("this is default implement")
}