package com.amazing.tengfei.aroutertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.amazing.tengfei.routerannotation.ARouter;

@ARouter(path = "/main/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
