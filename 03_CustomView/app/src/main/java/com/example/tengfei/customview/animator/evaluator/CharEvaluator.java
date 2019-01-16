package com.example.tengfei.customview.animator.evaluator;

import android.animation.TypeEvaluator;

/**
 * @author tengfei
 * date 2019/1/16 1:55 PM
 * email tengfeigo@outlook.com
 * description 自定义 evaluator 实现字母从 A 变化到 Z
 */
public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = startValue;
        int endInt = endValue;
        int currInt = (int) (startInt + (endInt - startInt) * fraction);
        return (char) currInt;
    }
}
