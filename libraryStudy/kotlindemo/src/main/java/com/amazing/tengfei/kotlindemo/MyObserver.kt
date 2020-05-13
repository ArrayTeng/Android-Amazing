package com.amazing.tengfei.kotlindemo

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @author 飞一般的感觉
 * date 2020/5/13 10:15 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
class MyObserver :LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop(){

    }
}