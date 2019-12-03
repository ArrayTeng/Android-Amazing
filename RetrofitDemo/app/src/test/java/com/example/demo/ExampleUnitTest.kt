package com.example.demo

import com.example.demo.retrofit.Call
import com.example.demo.retrofit.CallBack
import com.example.demo.retrofit.Response
import com.example.demo.retrofit.Retrofit
import com.example.demo.service.IService
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun retrofitDemoTest() {
        val retrofit = Retrofit.Builder()
            .url("http://gank.io/api/")
            .build()

        val service = retrofit.create(IService::class.java)
        val call = service.test("1", "2", "3")

        call.enqueue(object : CallBack<String> {
            override fun onFailure(call: Call<String>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {

            }
        })
    }


}
