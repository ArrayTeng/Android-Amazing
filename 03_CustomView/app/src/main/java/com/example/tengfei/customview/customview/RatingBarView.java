package com.example.tengfei.customview.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.example.tengfei.customview.R;

/**
 * @author tengfei
 */
public class RatingBarView extends View {

    private int maxRatingBar;
    private Bitmap noFocusBitmap, focusBitmap;
    private int ratingBarPadding = 10;
    private int mCurrentGrade;

    public RatingBarView(Context context) {
        this(context, null);
    }

    public RatingBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBarView);
        ratingBarPadding = typedArray.getDimensionPixelSize(R.styleable.RatingBarView_RatingBarView_Padding, sp2dp(ratingBarPadding));
        maxRatingBar = typedArray.getInteger(R.styleable.RatingBarView_RatingBarView_maxRating, maxRatingBar);
        int noFocus = typedArray.getResourceId(R.styleable.RatingBarView_RatingBarView_noFocus, R.mipmap.star_normal);
        int focus = typedArray.getResourceId(R.styleable.RatingBarView_RatingBarView_focus, R.mipmap.star_selected);
        noFocusBitmap = BitmapFactory.decodeResource(getResources(), noFocus);
        focusBitmap = BitmapFactory.decodeResource(getResources(), focus);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //控件的最终宽度为每一个星星的宽度加上星星之间的间距总和
        int measuredWidth = noFocusBitmap.getWidth() * maxRatingBar + (maxRatingBar - 1) * ratingBarPadding;
        int measuredHeight = noFocusBitmap.getHeight();
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < maxRatingBar; i++) {
            int x = i * noFocusBitmap.getWidth() + i * ratingBarPadding;
            if (mCurrentGrade > i) {
                canvas.drawBitmap(focusBitmap, x, 0, null);
            } else {
                canvas.drawBitmap(noFocusBitmap, x, 0, null);
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                int currentGrade = (int) (moveX / noFocusBitmap.getWidth() + 1);
                if (currentGrade < 0) {
                    currentGrade = 0;
                }
                if (currentGrade > maxRatingBar) {
                    currentGrade = maxRatingBar;
                }
                if (currentGrade == mCurrentGrade) {
                    return true;
                }
                mCurrentGrade = currentGrade;
                invalidate();
            default:
                break;
        }

        return true;
    }

    private int sp2dp(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }
}
