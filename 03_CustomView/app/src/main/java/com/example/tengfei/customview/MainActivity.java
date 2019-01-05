package com.example.tengfei.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tengfei.customview.activity.CircleProgressBarActivity;
import com.example.tengfei.customview.activity.ColorTrackPagerActivity;
import com.example.tengfei.customview.activity.ColorTrackTextViewActivity;
import com.example.tengfei.customview.activity.MyQqMovementActivity;
import com.example.tengfei.customview.activity.MyTextViewActivity;
import com.example.tengfei.customview.activity.ShapeVariableActivity;

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
            default:
                break;
        }
    }

    private <T> void skipActivity(Class<T> z) {
        startActivity(new Intent(MainActivity.this, z));
    }
}
