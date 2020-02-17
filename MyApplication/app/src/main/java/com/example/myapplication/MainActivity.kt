package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity_test"


    fun click(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(TAG,"onCreate")

        val okHttpClient = OkHttpClient()

        okHttpClient.newCall()


    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"onDestroy")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e(TAG,"onNewIntent")
    }

}
