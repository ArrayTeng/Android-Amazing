package com.example.tengfei.customview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;

import com.example.tengfei.customview.R;

/**
 * @author tengfei
 */
public class MyTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);
        NestedScrollView nestedScrollView = findViewById(R.id.nestedScrollView);
//        nestedScrollView.setFillViewport(true);
    }
}
