package com.example.tengfei.customview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.tengfei.customview.R;

/**
 * 自定义 TextView
 *
 * @author tengfei
 */
public class MyTextView extends View {

    private String mText;
    private int mTextSize = 15;
    private int mTextColor = Color.BLACK;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        mText = array.getString(R.styleable.MyTextView_text);
        mTextSize = array.getDimensionPixelSize(R.styleable.MyTextView_textSize,mTextSize);
        mTextColor = array.getColor(R.styleable.MyTextView_textColor,mTextColor);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
    }
}
