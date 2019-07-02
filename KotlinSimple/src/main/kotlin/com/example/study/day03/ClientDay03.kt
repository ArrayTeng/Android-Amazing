package com.example.study.day03

/**
 * @author tengfei
 */

// 1 密封类 ，所有的直接子类必须嵌套在父类中,使用 sealed 修饰的类默认是 open 的
sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int = when (e) {

    is Expr.Num -> e.value
    is Expr.Sum -> eval(e.right) + eval(e.left)
}

// 2 主构造方法和初始化语句块
open class User(val nickName: String = "feifei", val isSubscribed: Boolean = true) {
}

// 如果你的类有一个父类，那么主构造方法同样需要初始化父类
class TwitterUser(nickName: String, val age: Int) : User(nickName) {

}

class Secretive private constructor() {}

open class Button

class RadionButton : Button()

// 3 用不同的方式初始化父类
open class View {
    constructor(params01: String, params02: String) {}

    constructor(params01: String) {}

}

class MyButton : View {
    constructor(params01: String) : this(params01, "params02")
    constructor(params01: String, params02: String) : super(params01, params02)

}

// 4 实现在接口中申明的属性
interface IUser {
    val nickName: String
}

class PrivateUser(override val nickName: String) : IUser {}

class SubscribingUser(val email: String) : IUser {
    override val nickName: String
        get() = email.substringBefore("@")
}

class FaceBookUser(val userId: Int) : IUser {
    override val nickName: String = getFaceBookName(userId)

    fun getFaceBookName(userId: Int): String = userId.toString()
}

// 5 通过get和set访问支持字段
class UserTest(val name: String) {
    var address: String = "建湖"
        set(value) {
            println("$name   $field   $value")
            field = value
        }
}

// 6 修改访问器的可见性
class LengthCounter {
    var counter: Int = 0
        private set

    fun addWorld(world: String) {
        counter += world.length
    }
}

fun main(args: Array<String>) {

    val user: User = User()
    println("如果所有构造方法参数都有默认值，那么编译器会生成一个不带参数的构造方法来使用所有的默认值  ${user.nickName}  ${user.isSubscribed}")

    val lengthCounter = LengthCounter()
    lengthCounter.addWorld("hai")
    println(lengthCounter.counter)

}
