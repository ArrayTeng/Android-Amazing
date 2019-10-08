package com.example.specialeffects.view

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.specialeffects.R
import com.example.specialeffects.utils.px2dp

/**
 * @author tengfei
 * date 2019-10-07 20:57
 * email tengfeigo@outlook.com
 * description
 */
class CameraView
@JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera: Camera = Camera()

    private var bottomFlip = 0F
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    private var topFlip = 0F
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    private var flipRotation = 0F
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    companion object {
        val PADDING = px2dp(100F)
        val IMAGEWIDTH = px2dp(200F)
    }

    init {

        //固定写法用于适配
        camera.setLocation(0F, 0F, -6 * Resources.getSystem().displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制上半部分
        canvas.save()
        canvas.translate(PADDING + IMAGEWIDTH / 2, PADDING + IMAGEWIDTH / 2)
        canvas.rotate(-flipRotation)
        camera.save()
        camera.rotateX(topFlip)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-IMAGEWIDTH, -IMAGEWIDTH, IMAGEWIDTH, 0F)
        canvas.rotate(flipRotation)
        canvas.translate(-(PADDING + IMAGEWIDTH / 2), -(PADDING + IMAGEWIDTH / 2))
        canvas.drawBitmap(getAvatar(IMAGEWIDTH.toInt()), PADDING, PADDING, paint)
        canvas.restore()

        //绘制下半部分
        canvas.save()
        canvas.translate(PADDING + IMAGEWIDTH / 2, PADDING + IMAGEWIDTH / 2)
        canvas.rotate(-flipRotation)
        camera.save()
        camera.rotateX(bottomFlip)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas.clipRect(-IMAGEWIDTH, 0F, IMAGEWIDTH, IMAGEWIDTH)
        canvas.rotate(flipRotation)
        canvas.translate(-(PADDING + IMAGEWIDTH / 2), -(PADDING + IMAGEWIDTH / 2))
        canvas.drawBitmap(getAvatar(IMAGEWIDTH.toInt()), PADDING, PADDING, paint)
        canvas.restore()
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