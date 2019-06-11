package com.example.tengfei.jetpack

import android.arch.lifecycle.LifecycleObserver


class MainActivity :BaseActivity() {
    override fun initContentView(): Int {
        return R.layout.activity_main
    }

    override fun initLifecycleObserver(): LifecycleObserver {
        return MyPresenter(this)
    }


}
