package com.amazing.tengfei.kotlindemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * @author 飞一般的感觉
 * date 2020/5/13 11:52 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
object Repository {

    fun getUser(userId: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 10)
        return liveData
    }

}