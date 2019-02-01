package com.example.tengfei.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author tengfei
 * date 2019/2/1 3:06 PM
 * email tengfeigo@outlook.com
 * description 自定义侧边栏，按字母排序
 */
public class LetterSideBarView extends View {
    public String[] mLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};

    private Paint normalTextPaint, colorTextPaint;

    public LetterSideBarView(Context context) {
        this(context, null);
    }

    public LetterSideBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        normalTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        colorTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int textWidth = (int) normalTextPaint.measureText("A");
        int measuredWidth = textWidth + getPaddingRight() + getPaddingLeft();
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;
        for (int i = 0; i < mLetters.length; i++) {
            int letterY = i * itemHeight + itemHeight / 2 + getPaddingTop();
            Paint.FontMetrics fontMetrics = normalTextPaint.getFontMetrics();
            int dy = (int) ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
            int baseLine = letterY + dy;
            canvas.drawText(mLetters[i], getWidth() / 2 - normalTextPaint.measureText("A") / 2, baseLine, normalTextPaint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                break;
            default:
                break;
        }
        return true;
    }
}
