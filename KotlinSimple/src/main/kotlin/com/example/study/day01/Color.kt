package com.example.study.day01

import com.example.study.day01.Color.*


/**
 * @author tengfei
 */
enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0), ORANGE(255, 0, 0), YELLOW(255, 0, 0), GREEN(255, 0, 0), BLUE(255, 0, 0), INDIGO(255, 0, 0), VIOLET(255, 0, 0);

    fun rgb() = (r * 10 + g) + b
}

fun getMnemonic(color: Color) = when (color) {
    RED -> "RED"
    ORANGE -> "ORANGE"
    YELLOW -> "YELLOW"
    GREEN -> "GREEN"
    BLUE -> "BLUE"
    INDIGO -> "INDIGO"
    VIOLET -> "VIOLET"
}

fun isWarmth(color: Color): Boolean = when (color) {
    RED, ORANGE, YELLOW -> true
    GREEN, BLUE, INDIGO, VIOLET -> false
}


fun mix(color01: Color, color02: Color): String = when (setOf(color01, color02)) {

    setOf(RED, ORANGE) -> RED.name
    setOf(YELLOW, GREEN) -> ORANGE.name
    setOf(BLUE, INDIGO) -> VIOLET.name

    else -> throw Exception("no color")

}


fun mixOptimized(color01: Color, color02: Color): String = when {
    color01 == RED && color02 == ORANGE -> RED.name
    color01 == YELLOW && color02 == GREEN -> ORANGE.name
    color01 == BLUE && color02 == INDIGO -> VIOLET.name
    else -> throw Exception("no color")
}

