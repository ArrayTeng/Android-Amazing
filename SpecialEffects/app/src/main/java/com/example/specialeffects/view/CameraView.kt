package com.example.specialeffects.view

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.specialeffects.R

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

    init {
        camera.rotateX(30F)
        //固定写法用于适配
        camera.setLocation(0F, 0F, -6 * Resources.getSystem().displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制上半部分
        canvas.save()
        canvas.translate(400F, 400F)
        canvas.clipRect(-600 / 2, -600 / 2, 600 / 2, 0)
        canvas.translate(-400F, -400F)
        canvas.drawBitmap(getAvatar(600), 100F, 100F, paint)
        canvas.restore()

        //绘制下半部分
        canvas.save()
        canvas.translate(400F, 400F)
        camera.applyToCanvas(canvas)
        canvas.clipRect(-600 / 2, 0, 600 / 2, 600 / 2)
        canvas.translate(-400F, -400F)
        canvas.drawBitmap(getAvatar(600), 100F, 100F, paint)
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