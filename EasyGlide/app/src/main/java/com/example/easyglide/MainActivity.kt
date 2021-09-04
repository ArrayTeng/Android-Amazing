package com.example.easyglide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import simple.easy.glide.Glide

class MainActivity : AppCompatActivity() {

    lateinit var imageView:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)


    }

    fun bt(view: View) {
        Glide.with(this)
            .load("https://img1.baidu.com/it/u=4186787118,517350218&fm=26&fmt=auto&gp=0.jpg")
            .into(imageView)
    }

}