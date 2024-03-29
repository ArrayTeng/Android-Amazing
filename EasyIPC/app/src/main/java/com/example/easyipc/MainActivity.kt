package com.example.easyipc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ipclib.core.EasyBinderIPC

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //打开服务
        EasyBinderIPC.getInstance().open(this)


        //注册服务
        EasyBinderIPC.getInstance().register(ExampleSingle::class.java)

        ExampleSingle.getInstance().userInfo = UserInfo("tengfei","27")

    }

    fun skip(view: View) {

        val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)

    }
}