package com.example.componentbase.emptyservice

import com.example.componentbase.service.IAccountService

/**
 * @author tengfei
 * date 2019-10-31 16:24
 * email tengfeigo@outlook.com
 * description
 */
class EmptyAccountService : IAccountService {
    override fun isLogin(): Boolean = false

    override fun getAccountId(): String = ""
}