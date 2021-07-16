package com.example.easyipc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ipclib.core.EasyBinderIPC

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        EasyBinderIPC.getInstance().open(this)


    }

    fun getObject(view: View) {

    }

    fun getUser(view: View) {

    }


}