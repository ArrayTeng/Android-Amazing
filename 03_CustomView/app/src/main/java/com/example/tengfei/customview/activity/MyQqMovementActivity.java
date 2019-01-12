package com.example.tengfei.customview.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;

import com.example.tengfei.customview.R;
import com.example.tengfei.customview.view.QqStepView;

/**
 * @author tengfei
 * 仿 QQ 运动步数
 */
public class MyQqMovementActivity extends AppCompatActivity {

    private QqStepView qqStepView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_movement);
        qqStepView = findViewById(R.id.qqStepView);
        qqStepView.setStepMax(5000);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(4000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentStep = (float) animation.getAnimatedValue();
                qqStepView.setCurrentStep((int) currentStep);
            }
        });

        valueAnimator.start();

    }
}
