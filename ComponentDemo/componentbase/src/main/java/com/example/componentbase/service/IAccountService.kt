package com.example.componentbase.service

/**
 * @author tengfei
 * date 2019-10-31 16:25
 * email tengfeigo@outlook.com
 * description 定义组件向外提供数据的方法
 */
interface IAccountService {

    fun isLogin(): Boolean

    fun getAccountId(): String
}