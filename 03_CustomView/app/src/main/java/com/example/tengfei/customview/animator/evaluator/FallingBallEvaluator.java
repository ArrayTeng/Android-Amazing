package com.example.tengfei.customview.animator.evaluator;

import android.animation.TypeEvaluator;

import com.example.tengfei.customview.entity.Point;

/**
 * @author tengfei
 * date 2019/1/16 2:42 PM
 * email tengfeigo@outlook.com
 * description ： implementation of parabolic animation with custom evaluator
 */
public class FallingBallEvaluator implements TypeEvaluator<Point> {
    private Point point = new Point(0, 0);

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        point.x = (int) (startValue.x + (endValue.x - startValue.x) * fraction);
        if (fraction * 2 < 1) {
            point.y = (int) (startValue.y + fraction * 2 * (endValue.y - startValue.y));
        } else {
            point.y = endValue.y;
        }
        return point;
    }
}
