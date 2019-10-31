package com.example.componentbase

import com.example.componentbase.emptyservice.EmptyAccountService
import com.example.componentbase.service.IAccountService

/**
 * @author tengfei
 * date 2019-10-31 16:26
 * email tengfeigo@outlook.com
 * description
 */

object ServiceFactory {

    var iAccountService: IAccountService? = null
        get() {
            if (field == null) EmptyAccountService()
            return field
        }

}