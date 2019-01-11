package com.example.tengfei.customview.animator;

import android.animation.TypeEvaluator;

/**
 * @author tengfei
 * @description 自定义 Evaluator 实现倒序输出
 */
public class ReverseEvaluator implements TypeEvaluator<Integer> {

    /**
     *
     * @param fraction 动画执行的进度
     * @param startValue 开始的值
     * @param endValue 结束的值
     * @return 经过计算后动画所处的"位置值"
     */
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        //计算动画实际运行的距离
        int animatorDistance = (int) (fraction*(endValue - startValue));
        return endValue - animatorDistance;
    }
}
