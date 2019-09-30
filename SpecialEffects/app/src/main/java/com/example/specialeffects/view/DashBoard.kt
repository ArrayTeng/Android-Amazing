package com.example.specialeffects.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.specialeffects.utils.px2dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * @author tengfei
 * date 2019-09-30 14:29
 * email tengfeigo@outlook.com
 * description
 */
class DashBoard
@JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attributeSet, defStyleAttr) {

    private val pathEffectPath = Path()


    private var mPathDashPathEffect: PathDashPathEffect

    companion object {
        val RADIUS = px2dp(150F)
        const val ANGLE = 60F
        val LENGTH = px2dp(100F)
    }

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)


    init {
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = px2dp(2F)


        val path = Path()
        path.addArc(
            width / 2 - RADIUS, height / 2 - RADIUS, width / 2 + RADIUS, height / 2 + RADIUS,
            90 + ANGLE / 2, 360 - ANGLE
        )
        val pathMeasure = PathMeasure()
        pathMeasure.setPath(path, false)
        val length = pathMeasure.length


        pathEffectPath.addRect(0F, 0F, px2dp(2F), px2dp(10F), Path.Direction.CW)
        mPathDashPathEffect =
            PathDashPathEffect(
                pathEffectPath,
                (length - px2dp(2F)) / 20,
                0F,
                PathDashPathEffect.Style.ROTATE
            )

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawArc(
            width / 2 - RADIUS, height / 2 - RADIUS, width / 2 + RADIUS, height / 2 + RADIUS,
            90 + ANGLE / 2, 360 - ANGLE, false, paint
        )
        paint.pathEffect = mPathDashPathEffect
        canvas.drawArc(
            width / 2 - RADIUS, height / 2 - RADIUS, width / 2 + RADIUS, height / 2 + RADIUS,
            90 + ANGLE / 2, 360 - ANGLE, false, paint
        )

        paint.pathEffect = null

        canvas.drawLine(
            (width / 2).toFloat(), (height / 2).toFloat(),
            cos(Math.toRadians(getAngleFromMark(5).toDouble())).toFloat() * LENGTH + width / 2,
            (sin(Math.toRadians(getAngleFromMark(5).toDouble())) * LENGTH + height / 2).toFloat(),
            paint
        )
    }

    private fun getAngleFromMark(mark: Int) = (ANGLE / 2 + 90) + ((360 - ANGLE) / 20 * mark)
}