package com.example.tengfei.customview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.example.tengfei.customview.R;

/**
 * @author tengfei
 * 自定义 TextView  实现颜色可变效果
 */
public class ColorTrackTextView extends AppCompatTextView {

    private static final String TAG = "ColorTrackTextView";

    private Paint mOriginPaint, mChangePaint;
    private int mOriginColor = Color.RED, mChangeColor = Color.BLACK;
    private Rect bounds;

    private float mCurrentProgress = 0.6F;

    private Direction direction;

    public enum Direction {
        /**
         * 从左到右变换
         */
        LEFT_TO_RIGHT,
        /**
         * 从右到左变换
         */
        RIGHT_TO_LEFT
    }

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
        int middle = (int) (mCurrentProgress * getWidth());
        if (direction == Direction.LEFT_TO_RIGHT) {
            drawText(canvas, 0, middle, mChangePaint);
            drawText(canvas, middle, getWidth(), mOriginPaint);
        } else if (direction == Direction.RIGHT_TO_LEFT) {
            drawText(canvas, getWidth() - middle, getWidth(), mChangePaint);
            drawText(canvas, 0, getWidth() - middle, mOriginPaint);
        } else {
            drawText(canvas, 0, getWidth(), mOriginPaint);
        }

    }

    private void drawText(Canvas canvas, int left, int right, Paint paint) {
        String text = getText().toString();
        mChangePaint.getTextBounds(text, 0, text.length(), bounds);
        //计算出文本开始的位置
        int x = getWidth() / 2 - bounds.width() / 2;
        Paint.FontMetricsInt fontMetricsInt = mChangePaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        //计算基线
        int baseline = getHeight() / 2 + dy;
        //绘制不变色
        canvas.save();
        canvas.clipRect(left, 0, right, getHeight());
        canvas.drawText(text, x, baseline, paint);
        canvas.restore();
    }

    public void setCurrentProgress(float progress) {
        this.mCurrentProgress = progress;
        invalidate();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
