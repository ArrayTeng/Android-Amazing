package com.example.study.day01

/**
 * @author tengfei
 */
class Rectangle(val height: Int, val width: Int) {

    val isSquare:Boolean
        get() = height == width
}