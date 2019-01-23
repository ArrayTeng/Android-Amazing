package com.example.tengfei.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.tengfei.customview.R;

/**
 * @author tengfei
 * date 2019/1/22 11:08 PM
 * email tengfeigo@outlook.com
 * description 自定义字母索引列表
 */
public class LetterSideBar extends View {

    private Paint mPaint;

    private int mColor = Color.RED;
    private int mTextSize = 20;

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.LetterSideBar);
        mColor = typedArray.getColor(R.styleable.LetterSideBar_LetterSideBar_textColor,mColor);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.LetterSideBar_LetterSideBar_textSize,sp2px(mTextSize));
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        //动态调整
        mPaint.setColor(mColor);
        mPaint.setTextSize(mTextSize);
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //返回测量的文本的宽度
        int textWidth = (int) mPaint.measureText("A");
        int width = getPaddingLeft() + getPaddingRight() + textWidth;
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
