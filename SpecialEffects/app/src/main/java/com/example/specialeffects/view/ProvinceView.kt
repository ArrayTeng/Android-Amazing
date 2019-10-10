package com.example.specialeffects.view

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.specialeffects.utils.px2dp

/**
 * @author tengfei
 * date 2019-10-10 09:55
 * email tengfeigo@outlook.com
 * description
 */
class ProvinceView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    var province: String = "北京"
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.textSize = px2dp(14F)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText(province, (width / 2).toFloat(), (height / 2).toFloat(), paint)
    }

}