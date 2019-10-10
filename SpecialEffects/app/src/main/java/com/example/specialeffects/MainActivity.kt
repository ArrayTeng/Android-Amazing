package com.example.specialeffects

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.animation.TypeEvaluator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.specialeffects.utils.achieveAllCitys
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val objectAnimator = ObjectAnimator.ofObject(provinceView, "province",
            MyTypeEvaluator(),"台湾省")
        objectAnimator.startDelay = 3000
        objectAnimator.duration = 5000
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.start()
    }
}

class MyTypeEvaluator : TypeEvaluator<String> {
    override fun evaluate(fraction: Float, startValue: String?, endValue: String?): String {
        val startIndex = achieveAllCitys().indexOf(startValue)
        val endValueIndex = achieveAllCitys().indexOf(endValue)
        val targetIndex = (startIndex+(endValueIndex - startIndex))*fraction
        return achieveAllCitys()[targetIndex.toInt()]
    }
}

class MyInterpolator :TimeInterpolator {
    override fun getInterpolation(input: Float): Float {
        TODO()
    }
}
