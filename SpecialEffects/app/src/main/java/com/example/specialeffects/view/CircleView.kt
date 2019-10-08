package com.example.specialeffects.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.specialeffects.utils.px2dp

/**
 * @author tengfei
 * date 2019-10-08 21:47
 * email tengfeigo@outlook.com
 * description
 */
class CircleView
@JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private var radious = px2dp(50F)
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.color = Color.RED
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radious, paint)
    }
}