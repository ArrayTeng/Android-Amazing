package com.example.utils.widget.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.utils.utils.MyUtils;

/**
 * @author tengfei
 * date 2019/4/8 10:10 AM
 * email tengfeigo@outlook.com
 * description 自定义控件高仿 "花生地铁APP" 启动页倒计时控件,可以将此控件修改后放在放在任何需要倒计时需求的地方
 */
public class CountDownView extends View {

    private Paint outerPaint, innerPaint, textPaint;
    private Rect bounds;

    private int outerColor = Color.GREEN;
    private int innerColor = Color.RED;
    private int borderWidth = 0;
    private int textColor = Color.WHITE;
    private int textSize = 50;

    /**
     * 默认情况下开始的角度是 -90 度
     */
    private float startAngle = -90;
    /**
     * 扫过的角度应该是
     */
    private float sweepAngle;


    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化画笔对象
        initPaint();
        //初始化自定义属性
        initAttrs();
        //定义动画来根据值来计算当前进度
        initCountDownAnimator();
    }

    private void initCountDownAnimator() {
        ValueAnimator countDownAnimator = ValueAnimator.ofFloat(-90, 270);
        countDownAnimator.setDuration(10000);
        //在 5 秒的时间内将值从 0 到 360 平滑的移动
        countDownAnimator.addUpdateListener((ValueAnimator animation) -> {
            float currentValue = (float) animation.getAnimatedValue();
            //当前的进度
            float progress = Math.abs(currentValue / 360);
            //当前的角度
            float currentAngle = 360 * progress;
            if (currentValue < 0) {
                startAngle = -currentAngle;
            } else if (currentValue == 0) {
                startAngle = 0;
            } else if (currentValue > 0){
                startAngle = currentAngle;
            }
            invalidate();
        });
        countDownAnimator.start();
    }

    private void initAttrs() {

    }

    private void initPaint() {

        borderWidth = MyUtils.sp2px(2,this.getContext());

        //初始化外圆画笔对象
        innerPaint = new Paint();
        innerPaint.setColor(outerColor);
        innerPaint.setAntiAlias(true);
        innerPaint.setStyle(Paint.Style.FILL);

        //初始化内圆画笔对象
        outerPaint = new Paint();
        outerPaint.setColor(innerColor);
        outerPaint.setAntiAlias(true);
        outerPaint.setStrokeWidth(borderWidth);
        outerPaint.setStyle(Paint.Style.STROKE);
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
        //开始绘制内圆
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - borderWidth, innerPaint);
        //开始绘制外圆 在这里规定 startAngle 的范围就是 -90度 到 270度
        if (startAngle > 270 || startAngle < -90) {
            sweepAngle = 0;
        } else {
            sweepAngle = 270 - startAngle;
        }
        //绘制外围动态变化的进度条
        canvas.drawArc(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth / 2,
                getHeight() - borderWidth / 2, startAngle,
                sweepAngle, false, outerPaint);
        //绘制中间的文本
        textPaint.getTextBounds("跳过", 0, "跳过".length(), bounds);
        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseline = getHeight() / 2 + dy;
        canvas.drawText("跳过", getWidth() / 2 - bounds.width() / 2, baseline, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int measuredWidth = width < height ? width : height;
        int measuredHeight = width < height ? width : height;
        setMeasuredDimension(measuredWidth, measuredHeight);
    }


}
