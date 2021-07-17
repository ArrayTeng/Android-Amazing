package com.example.easyipc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ipclib.core.EasyBinderIPC

class SecondActivity : AppCompatActivity() {

    private var iExampleSingle:IExampleSingle?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        EasyBinderIPC.getInstance().open(this)


    }

    fun getObject(view: View) {

        iExampleSingle = EasyBinderIPC.getInstance().getInstance(IExampleSingle::class.java)

        //Log.e("tmd",iExampleSingle.toString())

    }

    fun getUser(view: View) {
        val userInfo = iExampleSingle?.userInfo
        Toast.makeText(this,userInfo?.name,Toast.LENGTH_SHORT).show()
    }


}