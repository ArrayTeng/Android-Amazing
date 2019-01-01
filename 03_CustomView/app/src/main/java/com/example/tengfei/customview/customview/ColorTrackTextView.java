package com.example.tengfei.customview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.example.tengfei.customview.R;

/**
 * @author tengfei
 * 自定义 TextView  实现颜色可变效果
 */
public class ColorTrackTextView extends AppCompatTextView {

    private Paint mOriginPaint, mChangePaint;
    private int mOriginColor = Color.RED, mChangeColor = Color.BLACK;
    private Rect bounds;

    private float mCurrentProgress = 0.6F;

    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs);
    }

    private void initPaint(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        mOriginColor = typedArray.getColor(R.styleable.ColorTrackTextView_originColor, mOriginColor);
        mChangeColor = typedArray.getColor(R.styleable.ColorTrackTextView_changeColor, mChangeColor);
        mOriginPaint = getPaintByColor(mOriginColor);
        mChangePaint = getPaintByColor(mChangeColor);
        typedArray.recycle();

        bounds = new Rect();
    }

    private Paint getPaintByColor(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setTextSize(getTextSize());
        paint.setColor(color);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //根据当前进度来计算出中间值
        int middle = (int) (mCurrentProgress*getWidth());
        String text = getText().toString();
        mChangePaint.getTextBounds(text, 0, text.length(), bounds);
        //计算出文本开始的位置
        int x = getWidth() / 2 - bounds.width() / 2;
        Paint.FontMetricsInt fontMetricsInt = mChangePaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top)/2 - fontMetricsInt.bottom;
        //计算基线
        int baseline = getHeight()/2 + dy;
        //绘制不变色
        canvas.save();
        canvas.clipRect(0,0,middle,getHeight());
        canvas.drawText(text,x,baseline, mOriginPaint);
        canvas.restore();
        //绘制变色
        canvas.save();
        canvas.clipRect(middle,0,getWidth(),getHeight());
        canvas.drawText(text,x,baseline, mChangePaint);
        canvas.restore();
    }
}
