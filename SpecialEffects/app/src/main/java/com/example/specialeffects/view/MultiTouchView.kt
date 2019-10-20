package com.example.specialeffects.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.specialeffects.R
import com.example.specialeffects.utils.dp2px

/**
 * @author tengfei
 * date 2019-10-21 01:27
 * email tengfeigo@outlook.com
 * description 多点触控学习
 */
class MultiTouchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var offsetX = 0F
    var offsetY = 0F
    var downX = 0F
    var downY = 0F
    var originalX = 0F
    var originalY = 0F

    var trackingPointId = 0

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                trackingPointId = event.getPointerId(0)
                downX = event.x
                downY = event.y
                originalX = offsetX
                originalY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                val index = event.findPointerIndex(trackingPointId)
                offsetX = originalX + event.getX(index) - downX
                offsetY = originalY + event.getY(index) - downY
                invalidate()
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                val actionIndex = event.actionIndex
                trackingPointId = event.getPointerId(actionIndex)
                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)
                originalX = offsetX
                originalY = offsetY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                var index: Int
                val actionIndex = event.actionIndex
                //获取抬起手指的id
                val pointId = event.getPointerId(actionIndex)
                //检查抬起手指的id是不是正在追踪的手指
                if (pointId == trackingPointId) {
                    index = if (actionIndex == event.pointerCount - 1) {
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }
                    trackingPointId = event.getPointerId(index)
                    downX = event.getX(index)
                    downY = event.getY(index )
                    originalX = offsetX
                    originalY = offsetY
                }
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(getAvatar(dp2px(200F).toInt()), offsetX, offsetY, paint)
    }


    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
    }


}