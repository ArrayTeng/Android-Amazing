package com.example.traffic.widget

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.traffic.R
import com.example.traffic.utils.showSoftKeyboard
import kotlinx.android.synthetic.main.content_edit_text.view.*


/**
 * @author tengfei
 * date 2019-09-25 17:47
 * email tengfeigo@outlook.com
 * description
 */

class ContentEditTextLayout : FrameLayout {

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)
    }

    private fun init(context: Context) {
        val contentView = LayoutInflater.from(context).inflate(R.layout.content_edit_text, this, false)
        addView(contentView)
        setOnClickListener {
            showSoftKeyboard(contentView.contentEditText, context)
        }



        contentView.contentEditText.setFilters(arrayOf(InputFilter.LengthFilter(180)))

        contentView.contentEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            @SuppressLint("SetTextI18n")
            override fun afterTextChanged(s: Editable?) {
                val length = s?.length

                contentView.contentEditTextViewCount.text = "$length/180"
            }
        })
    }

}
