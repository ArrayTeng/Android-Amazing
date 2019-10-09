package com.example.specialeffects

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.specialeffects.utils.px2dp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val keyframe0 = Keyframe.ofFloat(0F, px2dp(50F))
        val keyframe1 = Keyframe.ofFloat(0.5F,  px2dp(100F))
        val keyframe2 = Keyframe.ofFloat(1F, px2dp(50F))

        val propertyValuesHolder =
            PropertyValuesHolder.ofKeyframe("radious", keyframe0, keyframe1, keyframe2)

        val animator = ObjectAnimator.ofPropertyValuesHolder(circleView, propertyValuesHolder)
        animator.startDelay = 3000
        animator.duration = 5000
        animator.start()


    }
}
