package com.example.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.retrofit.Call
import com.example.demo.retrofit.CallBack
import com.example.demo.retrofit.Response
import com.example.demo.service.IService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = com.example.demo.retrofit.Retrofit.Builder()
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
