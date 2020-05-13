package com.amazing.tengfei.kotlindemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * @author 飞一般的感觉
 * date 2020/5/13 9:28 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
class MainViewModel(countReserved: Int) : ViewModel() {


    private val userLiveData = MutableLiveData<User>()

    val userName : LiveData<String> =Transformations.map(userLiveData,object :)

    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }

}