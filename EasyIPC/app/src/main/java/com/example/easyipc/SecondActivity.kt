package com.example.easyipc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ipclib.core.EasyBinderIPC

class SecondActivity : AppCompatActivity() {

    var iExampleSingle:IExampleSingle?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        EasyBinderIPC.getInstance().open(this)


    }

    fun getObject(view: View) {

        iExampleSingle = EasyBinderIPC.getInstance().getInstance(IExampleSingle::class.java,"tengfei","27岁",175)

    }

    fun getUser(view: View) {

    }


}