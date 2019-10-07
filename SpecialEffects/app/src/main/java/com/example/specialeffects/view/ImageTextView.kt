package com.example.specialeffects.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.specialeffects.R
import com.example.specialeffects.utils.px2dp

/**
 * @author tengfei
 * date 2019-10-02 09:13
 * email tengfeigo@outlook.com
 * description
 */
class ImageTextView@JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attributeSet, defStyleAttr) {

    private val paint:Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val measyredWidth:FloatArray = FloatArray(1)

    companion object{

        val  text:String = "Hello World,Hello World,Hello World,Hello World,Hello World,Hello" +
                " World,Hello World,Hello World,Hello World,Hello World,Hello World,Hello World,Hello World,Hello World,Hello World,Hello World,Hello World,Hello World,Hello " +
                "World,Hello World,Hello World,Hello World,Hello World,Hello World,Hello World,"
    }

    init {



    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.textSize = px2dp(20F)
        canvas.drawBitmap(getAvatar(200),width - px2dp(100F),px2dp(70F),paint)

        var oldIndex = 0

        var currentIndex = paint.breakText(text,true, width.toFloat(),measyredWidth)
        canvas.drawText(text,0,currentIndex,0F,50F,paint)
        oldIndex = currentIndex


        currentIndex = paint.breakText(text,currentIndex, text.length,true,width.toFloat(),measyredWidth)
        canvas.drawText(text,oldIndex,oldIndex+currentIndex,0F,50F+paint.fontSpacing,paint)



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