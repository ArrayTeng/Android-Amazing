package com.example.tengfei.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tengfei.customview.activity.MyTextViewActivity;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv01).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv01:
                skipActivity(MyTextViewActivity.class);
                break;
            default:
                break;
        }
    }

    private <T> void skipActivity(Class<T> z) {
        startActivity(new Intent(MainActivity.this, z));
    }
}
