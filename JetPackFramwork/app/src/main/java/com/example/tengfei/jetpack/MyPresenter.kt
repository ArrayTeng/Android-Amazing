package com.example.tengfei.jetpack

import android.content.Context
import android.util.Log

/**
 * @author tengfei
 * date 2019/6/11 4:11 PM
 * email tengfeigo@outlook.com
 * description
 */
@Suppress("UNREACHABLE_CODE")
class MyPresenter(private var context: Context) : LifecycleListener {

    override fun onCreate() {
        Log.i("tmd","onCreate"+context.javaClass.simpleName)
    }

    override fun onStart() {
        Log.i("tmd","onStart"+context.javaClass.simpleName)
    }

    override fun onResume() {
        Log.i("tmd","onResume"+context.javaClass.simpleName)
    }

    override fun onPause() {
        Log.i("tmd","onPause"+context.javaClass.simpleName)
    }

    override fun onStop() {
        Log.i("tmd","onStop"+context.javaClass.simpleName)
    }

    override fun onDestroy() {
        Log.i("tmd","onDestroy"+context.javaClass.simpleName)
    }


}