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

// 11 处理集合：可变参数、中缀调用和库的支持

val stringArrayVararg: Array<String> = arrayOf("Kotlin", "Java", "Js")

// 12 中缀调用 参考 http://www.imooc.com/article/287847
// 使用 “to” 来声明 map 的 key 与 value 之间的对应关系，这种形式的函数调用被称为中缀调用,
// 中缀调用可以与只有一个参数的函数一起使用，无论是普通的函数还是扩展函数。中缀符号需要通过 infix 修饰符来进行标记
// infix fun Any.to(other: Any) = Pair(this,other)
// 有时会有把一个对象解构成多个变量的需求，在 Kotlin 中这种语法称为解构声明

val pair = "滕飞" to "25"

// 13 kotlin 中分割字符串

// 14 正则表达式和三重引号字符串
fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("dir: $directory,name:$fileName extension:$extension")
}

// 15 局部函数
fun savePerson(person: Person) {
    person.validatePerson()
}

fun Person.validatePerson() {
    fun validate(value: String) {
        if (value.isNullOrEmpty()) {
            throw IllegalArgumentException("不可以保存 $name")
        }
    }
    validate(name)
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

    println("Kotlin中的展开运算${listOf("Args", *stringArrayVararg)}")

    val (name, age) = Person("tengfei", 25)

    val (x, y) = Point(1, 2)

    println("$x$y")

    println("12.234-56.89".split(".", "-"))

    parsePath("/users/yole/kotlin-book/chapter.adoc")

    val kotlinLogo = """| //
                        .| //
                        .| // \"""
    println(kotlinLogo.trimMargin("."))

    val price = """${'$'} 99.9"""
    println(price)
}