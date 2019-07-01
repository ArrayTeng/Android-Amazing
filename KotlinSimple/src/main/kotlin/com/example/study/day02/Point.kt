package com.example.study.day02

/**
 * @author tengfei
 */
class Point(val x: Int, val y: Int) {

    operator fun component1() = x
    operator fun component2() = y
}