package com.example.tengfei.customview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

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

    private Paint paint;


    private Rect widthBound;
    private Rect heightBound;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        mText = array.getString(R.styleable.MyTextView_myText);
        mTextSize = array.getDimensionPixelSize(R.styleable.MyTextView_myTextSize, sp2px(mTextSize));
        mTextColor = array.getColor(R.styleable.MyTextView_myTextColor, mTextColor);
        array.recycle();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(mTextSize);
        paint.setColor(mTextColor);

        widthBound = new Rect();
        heightBound = new Rect();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        int width = MeasureSpec.getSize(widthMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            paint.getTextBounds(mText, 0, mText.length(), widthBound);
            width = widthBound.width() + getPaddingLeft() + getPaddingRight();
        }

        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            paint.getTextBounds(mText, 0, mText.length(), heightBound);

            height = heightBound.height() + getPaddingTop() + getPaddingBottom() + paint.getFontMetricsInt().descent;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = getHeight() / 2 + dy;
        int x = getPaddingLeft();
        canvas.drawText(mText, x, baseLine, paint);
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }

}
