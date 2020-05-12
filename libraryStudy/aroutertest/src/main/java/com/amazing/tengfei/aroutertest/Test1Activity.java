package com.amazing.tengfei.aroutertest;

import android.os.Bundle;

import com.amazing.tengfei.routerannotation.ARouter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 飞一般的感觉
 * date 2020/5/8 9:39 PM
 * email arrayadapter.cn@gmail.com
 * description
 */

@ARouter(path = "/test/Test1Activity")
public class Test1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
    }
}
