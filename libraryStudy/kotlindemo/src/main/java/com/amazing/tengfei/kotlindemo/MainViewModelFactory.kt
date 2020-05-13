package com.amazing.tengfei.kotlindemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author 飞一般的感觉
 * date 2020/5/13 9:55 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val countReserved:Int):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(countReserved) as T
    }


}