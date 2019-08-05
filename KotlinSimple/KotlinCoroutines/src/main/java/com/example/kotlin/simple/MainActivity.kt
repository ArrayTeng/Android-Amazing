package com.example.kotlin.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.coroutines.*
import kotlinx.android.synthetic.main.activity_main.*
import java.security.cert.CertStoreSpi


class MainActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = "MainActivity.test"


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btDefault -> {
                GlobalScope.launch(start = CoroutineStart.DEFAULT) {
                    Log.i(TAG, "Default 启动模式")
                }
            }
            R.id.btAtomic -> {

            }
            R.id.btUndispatched -> {

            }
            R.id.btLazy -> {

            }
            R.id.btTestSuspend -> {
                GlobalScope.launch {
                    val token = getToken()
                    val info = createPost(token)
                    val response = processPost(token , info)
                    Log.i(TAG,response)
                }
            }
            else -> {

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btDefault.setOnClickListener(this)
        btAtomic.setOnClickListener(this)
        btUndispatched.setOnClickListener(this)
        btLazy.setOnClickListener(this)
        btTestSuspend.setOnClickListener(this)


    }

    suspend fun getToken(): String {
        delay(2000)
        return "Token"
    }

    suspend fun createPost(token: String): String {
        delay(2000)
        return "$token createPost"
    }

    suspend fun processPost(token: String, info: String): String {
        delay(2000)
        return "$token $info"
    }
}
