package com.example.specialeffects

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.specialeffects.utils.px2dp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ObjectAnimator.ofFloat(cameraView,"bottomFlip",  150F)
            .setDuration(3000)
            .start()


    }
}
