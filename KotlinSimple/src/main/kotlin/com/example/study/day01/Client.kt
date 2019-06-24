package com.example.study.day01

import com.example.study.day01.Color.*

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

    println("if 和 when 都可以使用代码块作为分支体，这种情况下代码块中的最后一个表达式就是结果 ${evalWithLogging(Sum(Num(2),Num(3)))}")

    /**
     * 迭代事物 while 和 for 循环
     */


}

fun max(a: Int, b: Int) = if (a > b) a else b