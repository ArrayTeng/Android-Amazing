package com.amazing.tengfei.kotlindemo

import kotlinx.coroutines.processNextEventInCurrentThread
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

}


class Money(val value:Int){

    operator fun plus(money: Money):Money{
        val sum = value+money.value
        return Money(sum)
    }
}


fun main() {
    val money01 = Money(20)

    val money02 = Money(30)


    val list = listOf(1,4,5,2,4,52,3,4,2,3)

     val list2 = list.map {
        it*10
    }.filter {
        it < 100
    }

    for (value in list2){
      //  println(value)
    }

    val result = mun1Andnum2(1,2) { n1, n2->n1+n2}

    print(result)


}


fun mun1Andnum2(num1 :Int,num2:Int,operation:(Int,Int)->Int):Int{
    return operation(num1,num2)
}

