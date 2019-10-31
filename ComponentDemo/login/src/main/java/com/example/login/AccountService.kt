package com.example.login

import com.example.componentbase.service.IAccountService

/**
 * @author tengfei
 * date 2019-10-31 17:34
 * email tengfeigo@outlook.com
 * description
 */
class AccountService : IAccountService {
    override fun getAccountId(): String {
        return "15001860976"
    }

    override fun isLogin(): Boolean {
        return true
    }

}