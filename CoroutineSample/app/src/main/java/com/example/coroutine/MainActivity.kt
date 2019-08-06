package com.example.coroutine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val tag = "MainActivity.test"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt.setOnClickListener {
            GlobalScope.launch {
                val responseBody = getUser().getUser("bennyhuo")
                Log.i(tag, responseBody.string())
            }
        }


    }

    private fun getUser(): GithubApi {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com")
                .build()
        return retrofit.create(GithubApi::class.java)
    }
}
