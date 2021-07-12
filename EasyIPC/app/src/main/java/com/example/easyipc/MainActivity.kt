package com.example.easyipc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ipclib.core.EasyBinderIPC

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //打开服务
        EasyBinderIPC.getInstance().open(this)

        //注册服务
        //EasyBinderIPC.getInstance().register()
    }
}