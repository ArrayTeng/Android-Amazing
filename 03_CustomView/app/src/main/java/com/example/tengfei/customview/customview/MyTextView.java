package com.example.tengfei.customview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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

    private Paint paint;

    private Rect heightRect;
    private Rect widthBound;

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
        mTextSize = array.getDimensionPixelSize(R.styleable.MyTextView_myTextSize, mTextSize);
        mTextColor = array.getColor(R.styleable.MyTextView_myTextColor, mTextColor);
        array.recycle();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(mTextSize);
        paint.setColor(mTextColor);

        heightRect = new Rect();
        widthBound = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        int width = MeasureSpec.getSize(widthMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {

            paint.getTextBounds(mText, 0, mText.length(), widthBound);
            width = widthBound.width();
        }

        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (heightMode == MeasureSpec.AT_MOST) {

            paint.getTextBounds(mText, 0, mText.length(), heightRect);
            height = heightRect.height();
        }

        setMeasuredDimension(width, height);
    }
}
