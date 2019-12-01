package com.example.demo

import com.example.demo.retrofit.Retrofit
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun retrofitDemoTest(){
        val retrofit = Retrofit.Builder()
            .url("")
            .build()

        val service  = retrofit.create(Service::class.java)
        service.test()
    }




}
