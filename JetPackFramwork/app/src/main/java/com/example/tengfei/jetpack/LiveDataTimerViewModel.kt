package com.example.tengfei.jetpack

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.SystemClock
import java.util.*

/**
 * @author tengfei
 * date 2019/6/12 10:21 PM
 * email tengfeigo@outlook.com
 * description  思考 1 ViewModel的作用，观看官方视频然后回答 2 liveData的作用以及应用范围
 */
class LiveDataTimerViewModel : ViewModel() {

    private var mInitialTime: Long = 0

    private val mElapsedTime: MutableLiveData<Long> = MutableLiveData()

    companion object {
        const val ONE_SECOND: Long = 1000
    }

    init {
        mInitialTime = SystemClock.elapsedRealtime()
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val longValue: Long = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(longValue)
            }
        }, ONE_SECOND, ONE_SECOND)
    }

    fun getElapsedTime():LiveData<Long>{
        return mElapsedTime
    }


}