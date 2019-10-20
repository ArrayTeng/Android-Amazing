package com.example.specialeffects.view

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import com.example.specialeffects.R
import com.example.specialeffects.utils.dp2px
import kotlin.math.max
import kotlin.math.min

/**
 * @author tengfei
 * date 2019-10-16 19:29
 * email tengfeigo@outlook.com
 * description
 */
class ScalableImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var offsetX = 0f
    private var offsetY = 0f

    private var originalOffsetX = 0F
    private var originalOffsetY = 0F

    private var smallScale = 0F
    private var bigScale = 0F

    private var big = false

    private val bitmap: Bitmap

    /**
     * 手势检测监听器
     */
    private val gestureDetector: GestureDetectorCompat

    /**
     * 手势缩放监听器
     */
    private val scaleGestureDetector: ScaleGestureDetector

    private val henOnScaleGestureListener = HenOnScaleGestureListener()
    private val henGestureListener = HenGestureListener()
    private val henOnFlingRunnable = HenOnFlingRunnable()

    private val overScroller: OverScroller

    private var currentScale: Float = 0F
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    private val scaleObjectAnimator: ObjectAnimator by lazy {
        //在View里定义了 scaleFraction 属性通过属性动画来操作这个动画的 scaleFraction 的值
        ObjectAnimator.ofFloat(
            this@ScalableImageView, "currentScale",
            smallScale, bigScale
        )

    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        bitmap = getAvatar(dp2px(100F).toInt())
        gestureDetector = GestureDetectorCompat(context, henGestureListener)
        scaleGestureDetector = ScaleGestureDetector(context, henOnScaleGestureListener)
        overScroller = OverScroller(context)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var result = scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress){
            result =  gestureDetector.onTouchEvent(event)
        }
        return result
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originalOffsetX = ((width - bitmap.width) / 2).toFloat()
        originalOffsetY = ((height - bitmap.height) / 2).toFloat()

        //图片的横向比较宽
        if ((bitmap.width / bitmap.height) > (width / height)) {
            smallScale = (width / bitmap.width) * 1.5.toFloat()
            bigScale = (height / bitmap.height) * 1.5.toFloat()
        } else {
            //图片的纵向比较高
            smallScale = (height / bitmap.height).toFloat()
            bigScale = (width / bitmap.width).toFloat()
        }
        this.currentScale = smallScale
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        canvas.scale(currentScale, currentScale, (width / 2).toFloat(), (height / 2).toFloat())
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
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

    private inner class HenOnScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {

        var initializerScale: Float = 0F

        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            initializerScale = currentScale
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
        }

        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            currentScale = initializerScale * detector!!.scaleFactor
            invalidate()
            return false
        }
    }

    private inner class HenOnFlingRunnable : Runnable {
        override fun run() {
            if (overScroller.computeScrollOffset()) {
                offsetX = overScroller.currX.toFloat()
                offsetY = overScroller.currY.toFloat()
                invalidate()
                postOnAnimation(this)
            }
        }
    }

    private inner class HenGestureListener : GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

        /**
         * 如果控件设置了双击，那么 onSingleTapUp 失效并且在 onDoubleTap 情况下单击由 onSingleTapConfirmed 处理
         */
        override fun onDoubleTap(e: MotionEvent): Boolean {
            big = !big
            if (big) {
                //双击的点在原地，本质上就是在放大后做一个逆向的偏移
                offsetX = (e.x - width / 2) - (e.x - width / 2) * (bigScale / smallScale)
                offsetY = (e.y - height / 2) - (e.y - height / 2) * (bigScale / smallScale)
                scaleObjectAnimator.start()
            } else {
                scaleObjectAnimator.reverse()
            }
            invalidate()
            return false
        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean = false

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean = false

        override fun onShowPress(e: MotionEvent?) = Unit

        override fun onSingleTapUp(e: MotionEvent?): Boolean = false

        //对应于 down 事件
        override fun onDown(e: MotionEvent?): Boolean = true

        //惯性滑动
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (big) {
                //左右滑动的最大偏移量
                val maxOffsetX: Int = ((bitmap.width * bigScale - width) / 2).toInt()
                //上下滑动的最大偏移量
                val maxOffsetY: Int = ((bitmap.height * bigScale - height) / 2).toInt()
                overScroller.fling(
                    offsetX.toInt(),
                    offsetY.toInt(),
                    velocityX.toInt(),
                    velocityY.toInt(),
                    -maxOffsetX,
                    maxOffsetX,
                    -maxOffsetY,
                    maxOffsetY
                )

                postOnAnimation(henOnFlingRunnable)
            }

            return false
        }

        override fun onScroll(
            downEvent: MotionEvent?,
            currentEvent: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (big) {
                //左右滑动的最大偏移量
                val maxOffsetX = (bitmap.width * bigScale - width) / 2
                //上下滑动的最大偏移量
                val maxOffsetY = (bitmap.height * bigScale - height) / 2
                offsetX -= distanceX
                offsetX = min(offsetX, maxOffsetX)
                offsetX = max(offsetX, -maxOffsetX)

                offsetY -= distanceY
                offsetY = min(offsetY, maxOffsetY)
                offsetY = max(offsetY, -maxOffsetY)

                invalidate()
            }
            return false
        }

        override fun onLongPress(e: MotionEvent?) = Unit

    }
}



