package com.example.tengfei.customview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.example.tengfei.customview.R;

/**
 * @author tengfei
 * date 2019/1/22 11:08 PM
 * email tengfeigo@outlook.com
 * description 自定义字母索引列表
 */
public class LetterSideBar extends View {

    private static final String TAG = LetterSideBar.class.getName();

    public String[] mLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};

    private Paint normalPaint;
    private Paint heightLightPaint;

    private int mColor = Color.RED;
    private int mTextSize = 15;

    /**
     * 当前触摸位置的字母
     */
    private String mCurrentTouchLetter;

    private String mLastTouchLetter;
    private int itemHeight;

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LetterSideBar);
        mColor = typedArray.getColor(R.styleable.LetterSideBar_LetterSideBar_textColor, mColor);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.LetterSideBar_LetterSideBar_textSize, sp2px(mTextSize));
        typedArray.recycle();

        //初始化高亮的画笔
        heightLightPaint = new Paint();
        heightLightPaint.setAntiAlias(true);
        heightLightPaint.setColor(Color.BLUE);
        heightLightPaint.setTextSize(mTextSize);

        normalPaint = new Paint();
        normalPaint.setAntiAlias(true);
        //动态调整
        normalPaint.setColor(mColor);
        normalPaint.setTextSize(mTextSize);
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //返回测量的文本的宽度
        int textWidth = (int) normalPaint.measureText("A");
        int width = getPaddingLeft() + getPaddingRight() + textWidth;
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;

        for (int i = 0; i < mLetters.length; i++) {
            int x = (int) (getWidth() / 2 - normalPaint.measureText(mLetters[i]) / 2);
            int letterY = i * itemHeight + itemHeight / 2 + getPaddingTop();
            // y 的位置为字母基线的位置
            Paint.FontMetrics fontMetrics = normalPaint.getFontMetrics();
            int dy = (int) ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
            int baseLine = letterY + dy;

            if (mLetters[i].equals(mCurrentTouchLetter)) {
                canvas.drawText(mLetters[i], x, baseLine, heightLightPaint);
            } else {
                canvas.drawText(mLetters[i], x, baseLine, normalPaint);
            }

        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float mCurrentY = event.getY();
                int mCurrentTouchPosition = (int) (mCurrentY / itemHeight);
                if (mCurrentTouchPosition < 0) {
                    mCurrentTouchPosition = 0;
                }
                if (mCurrentTouchPosition > mLetters.length - 1) {
                    mCurrentTouchPosition = mLetters.length - 1;
                }
                mCurrentTouchLetter = mLetters[mCurrentTouchPosition];
                if (currentTouchLetterListener != null) {
                    currentTouchLetterListener.currentTouchLetter(mCurrentTouchLetter);
                }
                if (!mCurrentTouchLetter.equals(mLastTouchLetter)){
                    mLastTouchLetter  =mCurrentTouchLetter;
                    invalidate();
                }

                break;
            default:
                break;

        }
        return true;
    }

    private CurrentTouchLetterListener currentTouchLetterListener;

    public void setCurrentTouchLetterListener(CurrentTouchLetterListener currentTouchLetterListener) {
        this.currentTouchLetterListener = currentTouchLetterListener;
    }

    public interface CurrentTouchLetterListener {
        /**
         * 回调当前触摸的字母
         * @param currentTouchLetter 当前触摸的字母
         */
        void currentTouchLetter(CharSequence currentTouchLetter);
    }
}
