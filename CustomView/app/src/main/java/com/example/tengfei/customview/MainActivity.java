package com.example.tengfei.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tengfei.customview.activity.BouquetLoadingActivity;
import com.example.tengfei.customview.activity.CircleProgressBarActivity;
import com.example.tengfei.customview.activity.ColorTrackPagerActivity;
import com.example.tengfei.customview.activity.ColorTrackTextViewActivity;
import com.example.tengfei.customview.activity.LetterSideBarActivity;
import com.example.tengfei.customview.activity.ListDataScreenActivity;
import com.example.tengfei.customview.activity.MyQqMovementActivity;
import com.example.tengfei.customview.activity.MyTextViewActivity;
import com.example.tengfei.customview.activity.ParabolicAnimationActivity;
import com.example.tengfei.customview.activity.RatingBarActivity;
import com.example.tengfei.customview.activity.ShapeVariableActivity;
import com.example.tengfei.customview.activity.ValueAnimatorActivity;


/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv01).setOnClickListener(this);
        findViewById(R.id.tv02).setOnClickListener(this);
        findViewById(R.id.tv03).setOnClickListener(this);
        findViewById(R.id.tv04).setOnClickListener(this);
        findViewById(R.id.tv05).setOnClickListener(this);
        findViewById(R.id.tv06).setOnClickListener(this);
        findViewById(R.id.tv07).setOnClickListener(this);
        findViewById(R.id.tv08).setOnClickListener(this);
        findViewById(R.id.tv09).setOnClickListener(this);
        findViewById(R.id.tv10).setOnClickListener(this);
        findViewById(R.id.tv11).setOnClickListener(this);
        findViewById(R.id.tv12).setOnClickListener(this);
        findViewById(R.id.tv13).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv01:
                skipActivity(MyTextViewActivity.class);
                break;
            case R.id.tv02:
                skipActivity(MyQqMovementActivity.class);
                break;
            case R.id.tv03:
                skipActivity(ColorTrackTextViewActivity.class);
                break;
            case R.id.tv04:
                skipActivity(ColorTrackPagerActivity.class);
                break;
            case R.id.tv05:
                skipActivity(CircleProgressBarActivity.class);
                break;
            case R.id.tv06:
                skipActivity(ShapeVariableActivity.class);
                break;
            case R.id.tv07:
                skipActivity(RatingBarActivity.class);
                break;
            case R.id.tv08:
                skipActivity(ValueAnimatorActivity.class);
                break;
            case R.id.tv09:
                skipActivity(ListDataScreenActivity.class);
                break;
            case R.id.tv10:
                skipActivity(ParabolicAnimationActivity.class);
                break;
            case R.id.tv11:
                skipActivity(BouquetLoadingActivity.class);
                break;
            case R.id.tv12:
                skipActivity(LetterSideBarActivity.class);
                break;
            case R.id.tv13:
                break;
            default:
                break;
        }
    }

    private <T> void skipActivity(Class<T> z) {
        startActivity(new Intent(MainActivity.this, z));
    }
}
