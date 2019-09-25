package com.example.traffic

import android.os.Bundle
import com.example.traffic.commom.BaseActivity

/**
 * @author tengfei
 * date 2019-09-25 12:48
 * email tengfeigo@outlook.com
 * description
 */
class KnockOutActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knock_out)
        setupToolbar()
    }
}