package com.example.tengfei.customview.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tengfei.customview.R;
import com.example.tengfei.customview.view.CircleProgressBarView;

/**
 * @author tengfei
 */
public class CircleProgressBarActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleProgressBarView circleProgressBarView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_circle_progress_bar);
        circleProgressBarView = findViewById(R.id.circle_progress_view);
        findViewById(R.id.bt_circle_progress).setOnClickListener(this);
        circleProgressBarView.setMaxProgress(100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_circle_progress:
                circleProgressBarView.setMaxProgress(4000);
                ValueAnimator valueAnimator = ObjectAnimator.ofInt(0,4000);
                valueAnimator.setDuration(5000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int progress = (int) animation.getAnimatedValue();
                        circleProgressBarView.setCurrentProgress(progress);
                    }
                });
                valueAnimator.start();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
