package com.example.study.day01

import com.example.study.day01.Color.*
import java.util.*

/**
 * @author tengfei
 */

fun main(args: Array<String>) {
    val name: String = if (args.isNotEmpty()) args[0] else "Kotlin"
    println("使用了字符串模版 $name")
    println("如果使用了 \$ 符号，需要对其进行转意")
    println("可以使用复杂的表达式并用\${}将它包裹住如：${if (args.isNotEmpty()) args[0] else "Kotlin"}，也可以直接在双引号里直接嵌套双引号，只要双引号在表达式里就行")
    println("Hello World" + max(4, 6))

    val person: Person = Person()
    person.isMarried = false
    val isMarried = person.isMarried
    val personName = person.name
    println("person 结婚了吗？$isMarried 他的名字是 $personName")

    val rectangle: Rectangle = Rectangle(10, 10)
    println("isRectangle ${rectangle.isSquare}")

    val randomRectangle: Rectangle = createRandomRectangle()
    println("randomRectangle ${randomRectangle.isSquare}")

    println("Kotlin 中定义枚举 ${Color.BLUE.rgb()}")

    println("Kotlin 中使用 when ${getMnemonic(Color.INDIGO)}   ${isWarmth(Color.BLUE)}")

    println("Kotlin 中使用 when，${mix(RED, ORANGE)}")

    println("Kotlin 中使用 when，如果没有给when表达式提供参数，那么分支条件就是Boolean值 ${mixOptimized(RED, ORANGE)}")

    println("${eval(Num(1))}")

    println("if 和 when 都可以使用代码块作为分支体，这种情况下代码块中的最后一个表达式就是结果 ${evalWithLogging(Sum(Num(2), Num(3)))}")

    for (i in 1..100) {
        print(fizzBuzzFirstToDown(i))
    }
    println()
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzzFirstToDown(i) + "  ")
    }
    println()
    for (i in 0 until 100) {
        print(fizzBuzzFirstToDown(i))
    }
    println()
    println()
    println()
    val binaryReps = TreeMap<Char, String>()
    for (c in 'A'..'F') {
        binaryReps[c] = Integer.toBinaryString(c.toInt())
    }
    for ((key, value) in binaryReps) {
        println("$key $value")
    }


    println()
    println()
    println()

    /**
     * 迭代集合时使用下标
     */
    val list = arrayListOf("10", "22", "145", "31")
    for ((index, element) in list.withIndex()) {
        println("$index $element")
    }

    /**
     * 使用"in"检查区间和集合的成员
     */
    println("${isLetter('f')}  ${isNotDigit(4)}")

    /**
     * 用 in 检查作为 when 分支
     */
    println(recognize('f'))

}

fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "lt is letter"
    in 'a'..'z', in 'A'..'Z' -> "lt is buzzer"
    else -> "I don't know"
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(i: Int) = i !in 0..10

fun max(a: Int, b: Int) = if (a > b) a else b

fun fizzBuzzFirstToDown(i: Int): String = when {
    i % 15 == 0 -> "fizzBuzz"
    i % 3 == 0 -> "fizz"
    i % 5 == 0 -> "Buzz"
    else -> i.toString()
}
