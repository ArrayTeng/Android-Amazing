package com.example.specialeffects.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.specialeffects.R
import com.example.specialeffects.utils.px2dp

/**
 * @author tengfei
 * date 2019-09-30 22:10
 * email tengfeigo@outlook.com
 * description
 */
class AvatarView @JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attributeSet, defStyleAttr) {

    private var bitmap: Bitmap

    private val paint: Paint

    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    private val rectF = RectF()

    companion object {
        val WIDTH = px2dp(300F)
        val PADDING = px2dp(50F)
        val EDGE_WIDTH = px2dp(10F)
    }

    init {
        bitmap = getAvater(width)
        paint = Paint(Paint.ANTI_ALIAS_FLAG)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        canvas.drawOval(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH, paint)
        val saved = canvas.saveLayer(rectF, paint)
        canvas.drawOval(PADDING + EDGE_WIDTH, PADDING + EDGE_WIDTH, PADDING + WIDTH - EDGE_WIDTH, PADDING + WIDTH - EDGE_WIDTH, paint);

        paint.xfermode = xfermode
        canvas.drawBitmap(bitmap, PADDING, PADDING, paint)
        paint.xfermode = null
        canvas.restoreToCount(saved)
    }


    private fun getAvater(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
    }
}