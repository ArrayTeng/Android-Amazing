package com.example.tengfei.customview.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

/**
 * @author tengfei
 * date 2019/1/17 2:58 PM
 * email tengfeigo@outlook.com
 * description 花束直播页面加载自定义控件
 */
public class BouquetLoading extends RelativeLayout {

    private CircleView mLeftCircle, mMiddleCircle, mRightCircle;

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
        addView(mMiddleCircle);
        addView(mRightCircle);
    }

    private CircleView getCircleView(Context context) {
        CircleView circleView = new CircleView(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(dip2dp(8), dip2dp(8));
        params.addRule(CENTER_IN_PARENT);
        circleView.setLayoutParams(params);
        return circleView;
    }


    @SuppressWarnings("SameParameterValue")
    private int dip2dp(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }
}
