package com.example.study.day01


/**
 * @author tengfei
 */
interface Expr {
}

fun eval(expr: Expr): Int = when (expr) {
    is Num -> {
        expr.value
    }

    is Sum -> {
        eval(expr.left) + eval(expr.right)
    }

    else -> {
        throw IllegalArgumentException("unknowing expressionx")
    }

}

fun evalWithLogging(expr: Expr): Int = when (expr) {
    is Num -> {
        println("${expr.value}")
        expr.value
    }
    is Sum -> {
        val left = evalWithLogging(expr.left)
        val right = evalWithLogging(expr.right)
        println("$left+$right")
        left + right
    }
    else -> {
        throw IllegalArgumentException("unknowing expressionx")
    }
}
