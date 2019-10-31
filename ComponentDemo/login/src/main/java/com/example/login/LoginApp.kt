package com.example.login

import android.app.Application
import com.example.componentbase.ServiceFactory

/**
 * @author tengfei
 * date 2019-10-31 17:43
 * email tengfeigo@outlook.com
 * description
 */
class LoginApp :Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceFactory.iAccountService = AccountService()
    }
}