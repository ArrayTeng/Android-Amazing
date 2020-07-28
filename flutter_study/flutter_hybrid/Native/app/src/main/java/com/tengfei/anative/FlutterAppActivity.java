package com.tengfei.anative;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.embedding.android.FlutterActivity;

/**
 * @author 滕飞
 * date 2020/7/28 10:10 AM
 * email arrayadapter.cn@outlook.com
 * description
 */
public class FlutterAppActivity extends FlutterActivity {

    public static final String KEY = "initParams";

    private String initParams;

    public static void startFlutterAppActivity(Context context,String params){
        Intent intent = new Intent(context,FlutterAppActivity.class);
        intent.putExtra(KEY,params);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParams = getIntent().getStringExtra(KEY);
    }

    @Nullable
    @Override
    public String getCachedEngineId() {
        return "MY_CACHED_ENGINE_ID";
    }

    /**
     * 重写 getInitialRoute 函数将native的数据携带到flutter模块中，在flutter模块中通过
     * window.defaultRouteName 获取携带过来的数据，记得flutter中导入'dart:ui'
     */
    @NonNull
    @Override
    public String getInitialRoute() {
        return initParams == null ? super.getInitialRoute() : initParams;
    }

}
