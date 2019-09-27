package com.example.traffic.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.traffic.R
import kotlinx.android.synthetic.main.layout_home_server.view.*

/**
 * @author tengfei
 * date 2019-09-27 20:26
 * email tengfeigo@outlook.com
 * description
 */
class HomeServerFunctionView : LinearLayout {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        val contentFunctionView = LayoutInflater.from(context).inflate(R.layout.layout_home_server, this)
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.HomeServerFunctionView)
        val title = typedArray.getString(R.styleable.HomeServerFunctionView_title)
        val subTitle = typedArray.getString(R.styleable.HomeServerFunctionView_subTitle)
        val contentSrc = typedArray.getResourceId(R.styleable.HomeServerFunctionView_contentSrc, R.mipmap.icon_policy_list)
        contentFunctionView.homeServerTitle.text = title
        contentFunctionView.homeServerSubTitle.text = subTitle
        contentFunctionView.homeServerImage.setImageResource(contentSrc)
        typedArray.recycle()
    }

}