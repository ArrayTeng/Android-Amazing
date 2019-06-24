package com.example.study.day01

import java.util.*

/**
 * @author tengfei
 */
class Rectangle(val height: Int, val width: Int) {

    val isSquare: Boolean
        get() = height == width
}


fun createRandomRectangle(): Rectangle {
    val random: Random = Random()
    return Rectangle(random.nextInt(), random.nextInt())
}