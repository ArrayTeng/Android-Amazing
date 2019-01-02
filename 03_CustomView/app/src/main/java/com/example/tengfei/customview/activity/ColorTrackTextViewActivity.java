package com.example.tengfei.customview.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tengfei.customview.R;
import com.example.tengfei.customview.customview.ColorTrackTextView;

/**
 * @author tengfei
 */
public class ColorTrackTextViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ColorTrackTextView colorTrackTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_track);
        colorTrackTextView = findViewById(R.id.color_track_text);
        findViewById(R.id.bt_track_left_to_right).setOnClickListener(this);
        findViewById(R.id.bt_track_right_to_left).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_track_left_to_right:
                colorTrackTextView.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
                ValueAnimator leftToRightValueAnimator = ObjectAnimator.ofFloat(0, 1);
                leftToRightValueAnimator.setDuration(3000);
                leftToRightValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        colorTrackTextView.setCurrentProgress((Float) animation.getAnimatedValue());
                    }
                });
                leftToRightValueAnimator.start();
                break;
            case R.id.bt_track_right_to_left:
                colorTrackTextView.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                ValueAnimator rightToLeftValueAnimator = ObjectAnimator.ofFloat(0, 1);
                rightToLeftValueAnimator.setDuration(3000);
                rightToLeftValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        colorTrackTextView.setCurrentProgress((Float) animation.getAnimatedValue());
                    }
                });
                rightToLeftValueAnimator.start();
                break;
            default:
                break;
        }
    }
}
