package tengfei.example.samplejvm

import kotlinx.coroutines.*

/**
 * @author tengfei
 * date 2019/8/6 3:55 PM
 * email tengfeigo@outlook.com
 * description
 */

suspend fun main() {
    val job:Job = GlobalScope.launch(start = CoroutineStart.DEFAULT) {
        coroutineContext[Job]?.cancel()
        println(1)
        delay(1000)
        println(2)
    }
    //job.start()
    println(3)

    val result = GlobalScope.async(start = CoroutineStart.LAZY) {
        println("Hello World")
    }.await()

    delay(10000)
    println(4)
}