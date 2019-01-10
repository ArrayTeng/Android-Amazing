package com.example.tengfei.customview.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.example.tengfei.customview.R;

/**
 * @author tengfei
 */
public class BounceLoadingLayout extends LinearLayout {

    private BounceLoadingView mBounceLoadingView;
    private View shadowView;
    private boolean isStopAnimator;

    public BounceLoadingLayout(Context context) {
        this(context, null);
    }

    public BounceLoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BounceLoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    private void initLayout() {
        inflate(getContext(), R.layout.view_loading, this);
        mBounceLoadingView = findViewById(R.id.bounce_loading_view);
        shadowView = findViewById(R.id.shadow_view);
        post(new Runnable() {
            @Override
            public void run() {
                //表示在 onResume 之后
                startFallAnimator();
            }
        });

    }

    private void startThrowUpAnimator() {
        if (isStopAnimator) {
            return;
        }
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mBounceLoadingView, "translationY", sp2px(70), 0);
        translationAnimator.setDuration(350);

        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(shadowView, "scaleX", 0.3F, 1F);
        scaleAnimator.setDuration(350);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationAnimator, scaleAnimator);
        animatorSet.setInterpolator(new DecelerateInterpolator());


        animatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                startRotation();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startFallAnimator();
            }
        });

        animatorSet.start();
    }

    private void startFallAnimator() {
        if (isStopAnimator) {
            return;
        }
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mBounceLoadingView, "translationY", 0, sp2px(70));
        translationAnimator.setDuration(350);

        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(shadowView, "scaleX", 1F, 0.3F);
        scaleAnimator.setDuration(350);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationAnimator, scaleAnimator);

        //定义插值器，下落的速率是越来越快的
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                mBounceLoadingView.exchangeShape();
                startThrowUpAnimator();
            }
        });
        animatorSet.start();
    }

    private void startRotation() {
        ObjectAnimator rotationAnimator = null;
        switch (mBounceLoadingView.getmCurrentShape()) {
            case Circle:
            case Square:
                rotationAnimator = ObjectAnimator.ofFloat(mBounceLoadingView, "rotation", 0, 180);
                break;
            case Triangle:
                rotationAnimator = ObjectAnimator.ofFloat(mBounceLoadingView, "rotation", 0, -120);
                break;
            default:
                break;
        }
        if (rotationAnimator != null) {
            rotationAnimator.setDuration(350);
            rotationAnimator.setInterpolator(new DecelerateInterpolator());
            rotationAnimator.start();
        }
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(INVISIBLE);
        mBounceLoadingView.clearAnimation();
        shadowView.clearAnimation();
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(this);
            removeAllViews();
        }
        isStopAnimator = true;
    }

    @SuppressWarnings("SameParameterValue")
    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                getResources().getDisplayMetrics());
    }

}
