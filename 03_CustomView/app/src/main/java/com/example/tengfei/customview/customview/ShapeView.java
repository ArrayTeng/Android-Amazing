package com.example.tengfei.customview.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author tengfei
 * @description custom shape variable widget
 */
public class ShapeView extends View {

    private Shape mCurrentShape = Shape.Circle;

    private Paint mPaint;

    private Path trianglePath;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        trianglePath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int measuredWidth = widthSize < heightSize ? widthMeasureSpec : heightSize;
        int measuredHeight = widthSize < heightSize ? widthMeasureSpec : heightSize;
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (mCurrentShape) {
            case Circle:
                int center = getWidth() / 2;
                mPaint.setColor(Color.RED);
                canvas.drawCircle(center, center, center, mPaint);
                break;
            case Square:
                mPaint.setColor(Color.YELLOW);
                canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
                break;
            case Triangle:
                mPaint.setColor(Color.BLUE);
                double angle = Math.toRadians(30);
                trianglePath.moveTo(getWidth() / 2, 0);
                trianglePath.lineTo(0, (float) ((getWidth() / 2) / Math.tan(angle)));
                trianglePath.lineTo(getWidth(), (float) ((getWidth() / 2) / Math.tan(angle)));
                trianglePath.close();
                canvas.drawPath(trianglePath, mPaint);
                break;
            default:
                break;
        }
    }

    public void exchange() {
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
