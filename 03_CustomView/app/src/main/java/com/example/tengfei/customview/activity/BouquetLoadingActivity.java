package com.example.tengfei.customview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tengfei.customview.R;
import com.example.tengfei.customview.view.BouquetLoading;

/**
 * @author tengfei
 * date 2019/1/18 9:44 AM
 * email tengfeigo@outlook.com
 * description 花束直播页面加载自定义控件
 */
public class BouquetLoadingActivity extends AppCompatActivity {

    private BouquetLoading bouquetLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bouquet_loading);
        bouquetLoading = findViewById(R.id.bouquet_loading);
        bouquetLoading.setVisibility(View.GONE);
    }
}
