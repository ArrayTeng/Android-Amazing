package com.example.tengfei.jetpack

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private lateinit var liveDataTimerViewModel: LiveDataTimerViewModel

    override fun initData() {
        liveDataTimerViewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel::class.java)
        subscribe()
    }

    override fun initContentView(): Int {
        return R.layout.activity_main
    }

    override fun initLifecycleObserver(): LifecycleObserver {
        return BasePresenter(this)
    }

    private fun subscribe() {
        val elapsedTimeObserver: Observer<Long> = Observer { t -> main_text.text = t.toString() }

        liveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }

}
