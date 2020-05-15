package com.amazing.tengfei.kotlindemo

import androidx.arch.core.util.Function
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

    private val userIdLiveData = MutableLiveData<String>()

//    val user:LiveData<User> = Transformations.switchMap(userIdLiveData){userId -> Repository.getUser(userId)}

    val user:LiveData<User> = MutableLiveData<User>()

    fun getUser(userId:String){
        userIdLiveData.value = userId
    }


    private val userLiveData = MutableLiveData<User>()

    val userName: LiveData<String> = Transformations.map(userLiveData) { input -> "${input?.firstName}${input?.lastName}" }

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