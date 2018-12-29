package com.example.tengfei.customview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.tengfei.customview.R;

/**
 * @author tengfei
 * 仿 QQ 运动步数
 */
public class QqStepView extends View {

    private int mOuterColor = Color.RED;
    private int mInnerColor = Color.BLUE;
    private int mBorderWidth = 20;
    private int mStepTextSize = 30;
    private int mStepTextColor = Color.RED;
    /**
     * mStepMax:最大步数
     * mCurrentStep：当前步数
     */
    private int mStepMax;
    private int mCurrentStep;
    private String mStepText;

    private Paint mOutPaint, mInnerPaint, mTextPaint;

    private Rect mTextBounds;

    public QqStepView(Context context) {
        this(context, null);
    }

    public QqStepView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QqStepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QqStepView);
        mOuterColor = typedArray.getColor(R.styleable.QqStepView_outerColor, mOuterColor);
        mInnerColor = typedArray.getColor(R.styleable.QqStepView_innerColor, mInnerColor);
        mBorderWidth = (int) typedArray.getDimension(R.styleable.QqStepView_borderWidth, mBorderWidth);
        mStepTextSize = typedArray.getDimensionPixelSize(R.styleable.QqStepView_stepTextSize, sp2px(mStepTextSize));
        mStepTextColor = typedArray.getColor(R.styleable.QqStepView_stepTextColor, mStepTextColor);
        typedArray.recycle();

        mTextBounds = new Rect();


        //初始化内圆画笔对象
        mOutPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutPaint.setStyle(Paint.Style.STROKE);
        mOutPaint.setColor(mOuterColor);
        mOutPaint.setStrokeWidth(mBorderWidth);
        mOutPaint.setStrokeCap(Paint.Cap.ROUND);
        //初始化外圆画笔对象
        mInnerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerPaint.setStyle(Paint.Style.STROKE);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeWidth(mBorderWidth);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);
        //初始化中间字体画笔对象
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mStepTextSize);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setColor(mStepTextColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width < height ? width : height, width < height ? width : height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制外圆，之所以这么处理是因为画的圆弧的外边界也是有宽度的
        canvas.drawArc(mBorderWidth / 2, mBorderWidth / 2, getWidth() - mBorderWidth / 2,
                getWidth() - mBorderWidth / 2, 135, 270, false, mOutPaint);

        if (mStepMax == 0) {
            return;
        }
        float sweepAngle = (float) mCurrentStep / mStepMax;
        mStepText = mCurrentStep + "";
        //绘制内圆
        canvas.drawArc(mBorderWidth / 2, mBorderWidth / 2, getWidth() - mBorderWidth / 2,
                getWidth() - mBorderWidth / 2, 135, sweepAngle * 270, false, mInnerPaint);
        //绘制中间的文本
        mTextPaint.getTextBounds(mStepText, 0, mStepText.length(), mTextBounds);
        int dx = getWidth() / 2 - mTextBounds.width() / 2;
        Paint.FontMetricsInt mFontMetricsInt = mTextPaint.getFontMetricsInt();
        int dy = (mFontMetricsInt.bottom - mFontMetricsInt.top) / 2 - mFontMetricsInt.bottom;
        int baseline = getHeight() / 2 + dy;
        //绘制文本

        canvas.drawText(mStepText, dx, baseline, mTextPaint);

    }

    public int setStepMax(int mStepMax) {
        this.mStepMax = mStepMax;
        return mStepMax;
    }

    public int setCurrentStep(int mCurrentStep) {
        this.mCurrentStep = mCurrentStep;
        invalidate();
        return mCurrentStep;
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }
}
