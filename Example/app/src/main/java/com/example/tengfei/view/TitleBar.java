package com.example.tengfei.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * @author tengfei
 * date 2019/2/1 1:58 PM
 * email tengfeigo@outlook.com
 * description 自定义项目通用TitleBar
 */
public class TitleBar extends LinearLayout {

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
