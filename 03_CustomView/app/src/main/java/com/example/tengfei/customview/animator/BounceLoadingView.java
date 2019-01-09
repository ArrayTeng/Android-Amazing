package com.example.tengfei.customview.animator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;


/**
 * @author tengfei
 */
public class BounceLoadingView extends View {


    private Shape mCurrentShape = Shape.Circle;

    private Paint mPaint;
    private Path trianglePath;

    public BounceLoadingView(Context context) {
        this(context, null);
    }

    public BounceLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BounceLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        trianglePath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width < height ? width : height, width < height ? width : height);
    }


    public Shape getmCurrentShape() {
        return mCurrentShape;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvasPicture(canvas);
    }

    private void canvasPicture(Canvas canvas) {
        int center = getWidth() / 2;
        switch (mCurrentShape) {
            case Circle:
                mPaint.setColor(Color.RED);
                canvas.drawCircle(center, center, center, mPaint);
                break;
            case Square:
                mPaint.setColor(Color.BLUE);
                canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
                break;
            case Triangle:
                mPaint.setColor(Color.GREEN);
                double radian = Math.toRadians(30);
                trianglePath.moveTo(center, 0);
                trianglePath.lineTo(0, (float) (center / Math.tan(radian)));
                trianglePath.lineTo(getWidth(), (float) (center / Math.tan(radian)));
                trianglePath.close();
                canvas.drawPath(trianglePath, mPaint);
                break;
            default:
                break;
        }
    }

    public void exchangeShape() {
        switch (mCurrentShape) {
            case Circle:
                mCurrentShape = Shape.Square;
                break;
            case Square:
                mCurrentShape = Shape.Triangle;
                break;
            case Triangle:
                mCurrentShape = Shape.Circle;
                break;
            default:
                break;
        }
        invalidate();
    }

    public enum Shape {
        /**
         * 圆 矩形 三角
         */
        Circle,
        Square,
        Triangle
    }

}
