package com.example.specialeffects.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.specialeffects.utils.px2dp

/**
 * @author tengfei
 * date 2019-10-01 19:29
 * email tengfeigo@outlook.com
 * description
 */
class SportView @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var startAngle = 0F

    private val fontMetrics: Paint.FontMetrics = Paint.FontMetrics()

    private val rect:Rect = Rect()

    companion object {
        val RADIUS = px2dp(150F)
        val RING_WIDTH = px2dp(20F)
        val CIRCLE_COLOR = Color.parseColor("#90A4AE")
        val HIGH_LIGHT_COLOR = Color.parseColor("#FF4081")
    }

    init {
        paint.textSize = px2dp(50F)
        paint.typeface = Typeface.createFromAsset(context.assets, "Quicksand-Regular.ttf")
        paint.textAlign = Paint.Align.CENTER
        paint.getFontMetrics(fontMetrics)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = CIRCLE_COLOR
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = RING_WIDTH
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), RADIUS, paint)

        paint.color = HIGH_LIGHT_COLOR
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2 - RADIUS,
            height / 2 - RADIUS,
            width / 2 + RADIUS,
            height / 2 + RADIUS,
            startAngle - 90,
            startAngle + 80,
            false,
            paint
        )

        paint.style = Paint.Style.FILL





        val offset = (fontMetrics.ascent + fontMetrics.descent) / 2
        canvas.drawText(
            "Hello",
            (width / 2).toFloat(), height/2 - offset, paint
        )


    }

}