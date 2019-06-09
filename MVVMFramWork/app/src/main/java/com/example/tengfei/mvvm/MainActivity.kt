package com.example.tengfei.mvvm

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.tengfei.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val user = User()
        user.name = "fasf"
        user.header="http://k.zol-img.com.cn/sjbbs/7692/a7691515_s.jpg"
        activityMainBinding.user = user
        Glide.with(activityMainBinding.mainImage.context).load("http://k.zol-img.com.cn/sjbbs/7692/a7691515_s.jpg").into(activityMainBinding.mainImage)

       // user.name = "flalalafaf"
    }
}
