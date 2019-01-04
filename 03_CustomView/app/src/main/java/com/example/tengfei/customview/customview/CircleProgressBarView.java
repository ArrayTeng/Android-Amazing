package com.example.tengfei.customview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.tengfei.customview.R;

/**
 * @author tengfei
 */
public class CircleProgressBarView extends View {

    private Paint outerPaint, innerPaint, textPaint;

    private Rect bounds;

    private int outerColor = Color.GREEN;
    private int innerColor = Color.RED;
    private int currentProgress;
    private int maxProgress = 0;
    private int defaultProgress = 0;
    private int borderWidth = 0;
    private int textColor = Color.BLUE;
    private int textSize = 50;


    public CircleProgressBarView(Context context) {
        this(context, null);
    }

    public CircleProgressBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBarView);
        outerColor = array.getColor(R.styleable.CircleProgressBarView_CircleProgressBar_outerColor, outerColor);
        innerColor = array.getColor(R.styleable.CircleProgressBarView_CircleProgressBar_innerColor, innerColor);
        currentProgress = array.getInt(R.styleable.CircleProgressBarView_CircleProgressBar_currentProgress, defaultProgress);
        maxProgress = array.getInt(R.styleable.CircleProgressBarView_CircleProgressBar_maxProgress, maxProgress);
        defaultProgress = array.getInt(R.styleable.CircleProgressBarView_CircleProgressBar_defaultProgress, defaultProgress);
        borderWidth = (int) array.getDimension(R.styleable.CircleProgressBarView_CircleProgressBar_borderWidth, borderWidth);
        textColor = array.getColor(R.styleable.CircleProgressBarView_CircleProgressBar_textColor, textColor);
        textSize = array.getDimensionPixelSize(R.styleable.CircleProgressBarView_CircleProgressBar_textSize, sp2px(textSize));
        array.recycle();
    }

    private void initPaint() {
        //初始化外圆画笔对象
        outerPaint = new Paint();
        outerPaint.setColor(outerColor);
        outerPaint.setAntiAlias(true);
        outerPaint.setStrokeWidth(borderWidth);
        outerPaint.setStyle(Paint.Style.STROKE);
        //初始化内圆画笔对象
        innerPaint = new Paint();
        innerPaint.setColor(innerColor);
        innerPaint.setAntiAlias(true);
        innerPaint.setStrokeWidth(borderWidth);
        innerPaint.setStyle(Paint.Style.STROKE);
        //初始化中间文本画笔对象
        textPaint = new TextPaint();
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        //初始化中间文本 Rect 对象
        bounds = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //开始绘制外圆
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - borderWidth / 2, outerPaint);

        //扫过的角度
        if (maxProgress == 0) {
            return;
        }
        float sweepAngle = (float) currentProgress / maxProgress;
        //开始绘制内圆
        canvas.drawArc(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth / 2,
                getHeight() - borderWidth / 2, 0,
                sweepAngle * 360, false, innerPaint);
        //绘制中间文本
        String content = (int) (sweepAngle * 100) + "%";

        textPaint.getTextBounds(content, 0, content.length(), bounds);
        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseline = getHeight() / 2 + dy;
        canvas.drawText(content, getWidth() / 2 - bounds.width() / 2, baseline, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int measuredWidth = width < height ? width : height;
        int measuredHeight = width < height ? width : height;
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
        invalidate();
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }
}
