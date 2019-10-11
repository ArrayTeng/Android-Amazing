package com.example.specialeffects.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.specialeffects.utils.dp2px

/**
 * @author tengfei
 * date 2019-10-10 22:52
 * email tengfeigo@outlook.com
 * description
 */
class MaterialEditText @JvmOverloads
constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.editTextStyle
) :
    AppCompatEditText(context, attributeSet, defStyleAttr) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var glabelShow: Boolean = false

    private var glabelPercentage: Float = 0F
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    companion object {
        val GLABEL_HEIGHT: Float = dp2px(18F)
        val GLABEL_HEIGHT_MARGIN_LEFT = dp2px(5F)
        val GLABEL_HEIGHT_MARGIN_TOP = dp2px(2F)
        val GLABEL_HEIGHT_MARGIN_BOTTOM = dp2px(0F)
    }

    init {
        setPadding(
            paddingLeft,
            (paddingTop + GLABEL_HEIGHT + GLABEL_HEIGHT_MARGIN_TOP + GLABEL_HEIGHT_MARGIN_BOTTOM).toInt(),
            paddingRight,
            paddingBottom
        )

        paint.textSize = dp2px(13F)

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (glabelShow && TextUtils.isEmpty(s)) {
                    glabelShow = false
                    val objectAnimatorStart =
                        ObjectAnimator.ofFloat(this@MaterialEditText, "glabelPercentage", 0F)
                    objectAnimatorStart.start()
                } else if (!glabelShow && !TextUtils.isEmpty(s)) {
                    glabelShow = true
                    val objectAnimatorEnd =
                        ObjectAnimator.ofFloat(this@MaterialEditText, "glabelPercentage", 1F)
                    objectAnimatorEnd.start()
                }
            }
        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
            paint.alpha = (0xff * glabelPercentage).toInt()
            canvas.drawText(
                hint.toString(),
                GLABEL_HEIGHT_MARGIN_LEFT,
                GLABEL_HEIGHT + GLABEL_HEIGHT_MARGIN_TOP,
                paint
            )

    }


}