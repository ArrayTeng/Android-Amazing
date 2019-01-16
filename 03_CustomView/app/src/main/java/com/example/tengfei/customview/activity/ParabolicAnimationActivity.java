package com.example.tengfei.customview.activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.tengfei.customview.R;
import com.example.tengfei.customview.animator.evaluator.FallingBallEvaluator;
import com.example.tengfei.customview.entity.Point;

/**
 * @author tengfei
 * date 2019/1/16 2:49 PM
 * email tengfeigo@outlook.com
 * description
 */
public class ParabolicAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView parabolicImage;

    private ValueAnimator valueAnimator;

    private Point mPoint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parabolic_animation);
        findViewById(R.id.parabolic_tv).setOnClickListener(this);
        parabolicImage = findViewById(R.id.parabolic_img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.parabolic_tv:
                startParabolicAnimation();
                break;
            default:
                break;
        }
    }

    private void startParabolicAnimation() {
        // 创建一个属性动画，动画的作用是修改 Point 对象 x y 属性的值
        valueAnimator = ValueAnimator.ofObject(new FallingBallEvaluator(), new Point(0, 0), new Point(500, 500));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPoint = (Point) animation.getAnimatedValue();
                parabolicImage.layout(mPoint.x, mPoint.y, mPoint.x + parabolicImage.getWidth(), mPoint.y + parabolicImage.getHeight());
            }
        });
        valueAnimator.setDuration(4000);
        valueAnimator.start();
    }
}
