//@file:JvmName("StringUtils")

package com.example.study.day02

/**
 * 函数的定义与调用
 */

// 1 创建一个 set 集合
val set = hashSetOf(1, 5, 7)

// 2 创建一个 list
val list = arrayListOf(1, 5, 7)

// 3 创建一个 map
val map = hashMapOf(1 to "1", 2 to "2", 3 to "3")

// 4 自定义方法,使用 @JvmOverloads 注解，那么 joinToString 就会生成重载函数
@JvmOverloads
fun <T> joinToString(collection: Collection<T>, separator: String = ",", prefix: String = " ", postfix: String = " "): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// 5 在Kotlin中可以在申明函数的时候指定参数的默认值，看 4

// 6 要改变包含Kotlin顶层函数的生成类的名称，需要为这个文件添加 @JvmName 的注解，将其放到这个文件的开头，位于包前面

// 7 和函数一样，属性也可以放到文件的顶层，这个值会被存储到一个静态的字段中

var opCount = 0

fun PerformOperation() {
    opCount++
}

fun reportOperationCount() {
    println("$opCount")
}

// 8 也可以使用顶层函数定义常量,使用 const 修饰
const val UNIX_LINE_SEPARATOR = "\n"

// Kotlin 中的扩展函数和属性
fun String.lastChar(): Char = get(length - 1)

//声明扩展函数 joinToString
fun <T> Collection<T>.joinToStringExtend(separator: String = ",", prefix: String = " ", postfix: String = " "): String {
    val result = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) {
            result.append(separator)
        }
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// 9 扩展函数不会被重写，因为扩展函数在java中会被编译为静态函数，如果一个类的成员函数和扩展函数有相同的签名时，成员函数会被优先使用

// 10 添加扩展属性
val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) {
        this.setCharAt(length - 1, value)
    }

fun main(arrays: Array<String>) {
    //查看 1 2 3 三项的对象类型
    println(" 1 ${set.javaClass}")

    //在Kotlin中遍历list集合有两种方式，一个是 forEach 一个是 in 的方式
    list.forEach {
        println("$it")
    }

    println()

    for (value in list) {
        println(value)
    }


    println("获取集合中的最后一个值${list.last()}")

    println("获取 list 集合中最大的一个值 ${list.max()} 获取 set 集合中最大的一个值 ${set.max()}")

    println(joinToString(collection = list, separator = ";", prefix = "{", postfix = "}"))

    println("现在可可以在指定所有参数或者指定部分参数${joinToString(collection = list)}")

    println("Kotlin".lastChar())

    println("扩展Collection对象${list.joinToStringExtend(separator = ",", postfix = "}", prefix = "{")}")

}