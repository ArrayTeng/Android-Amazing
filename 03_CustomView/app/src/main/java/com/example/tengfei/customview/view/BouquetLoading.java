package com.example.tengfei.customview.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * @author tengfei
 * date 2019/1/17 2:58 PM
 * email tengfeigo@outlook.com
 * description 花束直播页面加载自定义控件,在使用动画的时候一定要切记及时关闭动画，否则容易导致内存泄漏
 */
public class BouquetLoading extends RelativeLayout {

    private CircleView mLeftCircle, mMiddleCircle, mRightCircle;

    private int animationDistance = 70;

    private long animatorSetDuration = 400;

    private boolean isStopAnimator;

    public BouquetLoading(Context context) {
        this(context, null);
    }

    public BouquetLoading(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BouquetLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLeftCircle = getCircleView(context);
        mLeftCircle.exchangeColor(Color.BLUE);
        mMiddleCircle = getCircleView(context);
        mMiddleCircle.exchangeColor(Color.GREEN);
        mRightCircle = getCircleView(context);
        mRightCircle.exchangeColor(Color.RED);

        addView(mLeftCircle);
        addView(mRightCircle);
        addView(mMiddleCircle);

        post(new Runnable() {
            @Override
            public void run() {
                if (isStopAnimator) {
                    return;
                }
                expendAnimation();
            }
        });
    }

    /**
     * 展开动画
     */
    private void expendAnimation() {
        ObjectAnimator leftAnimator = ObjectAnimator.ofFloat(mLeftCircle, "translationX", 0, -animationDistance);
        ObjectAnimator rightAnimator = ObjectAnimator.ofFloat(mRightCircle, "translationX", 0, animationDistance);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(leftAnimator, rightAnimator);
        animatorSet.setDuration(animatorSetDuration);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                closedAnimation();
            }
        });
        animatorSet.start();
    }

    /**
     * closed animation
     */
    private void closedAnimation() {
        ObjectAnimator leftAnimator = ObjectAnimator.ofFloat(mLeftCircle, "translationX", -animationDistance, 0);
        ObjectAnimator rightAnimator = ObjectAnimator.ofFloat(mRightCircle, "translationX", animationDistance, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(leftAnimator, rightAnimator);
        animatorSet.setDuration(animatorSetDuration);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                int leftColor = mLeftCircle.getColor();
                int middleColor = mMiddleCircle.getColor();
                int rightColor = mRightCircle.getColor();

                mLeftCircle.exchangeColor(middleColor);
                mMiddleCircle.exchangeColor(rightColor);
                mRightCircle.exchangeColor(leftColor);
                expendAnimation();
            }
        });
        animatorSet.start();
    }

    private CircleView getCircleView(Context context) {
        CircleView circleView = new CircleView(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dip2px(10), dip2px(10));
        params.addRule(CENTER_IN_PARENT);
        circleView.setLayoutParams(params);
        return circleView;
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        mLeftCircle.clearAnimation();
        mMiddleCircle.clearAnimation();
        mRightCircle.clearAnimation();
        this.clearAnimation();
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(this);
            removeAllViews();
        }
        isStopAnimator = true;
    }

    @SuppressWarnings("SameParameterValue")
    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }
}
