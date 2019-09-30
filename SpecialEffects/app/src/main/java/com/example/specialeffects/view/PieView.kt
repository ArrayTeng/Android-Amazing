package com.example.specialeffects.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.specialeffects.utils.px2dp
import kotlin.math.cos

/**
 * @author tengfei
 * date 2019-09-30 20:20
 * email tengfeigo@outlook.com
 * description
 */

class PieView
@JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private lateinit var rectF: RectF
    var currentAngle: Float = 0F

    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    companion object {
        val RADIUS = px2dp(150F)
        val LENGTH = px2dp(10F)
        val angles = arrayOf(60F, 100F, 120F, 80F)
        val colors = arrayOf("#2079FF", "#C2185B", "#009688", "#FF8F00")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF = RectF(
            width / 2 - RADIUS,
            height / 2 - RADIUS,
            width / 2 + RADIUS,
            height / 2 + RADIUS
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        for (index in 0 until angles.size) {
            paint.color = Color.parseColor(colors[index])
            canvas.save()
            if (index == 2){
                canvas.translate(cos(Math.toRadians((currentAngle+ angles[index]/2).toDouble())).toFloat() * LENGTH,
                    kotlin.math.sin(Math.toRadians((currentAngle+ angles[index]/2).toDouble())).toFloat() * LENGTH
                )
            }
            canvas.drawArc(rectF, currentAngle, angles[index], true, paint)
            canvas.restore()

            currentAngle += angles[index]
        }


    }


}