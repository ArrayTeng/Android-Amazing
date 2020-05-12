package com.amazing.tengfei.aroutertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.amazing.tengfei.aroutercore.EasyRouter;
import com.amazing.tengfei.routerannotation.ARouter;

@ARouter(path = "/main/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void click(View view) {
        EasyRouter.getInstance().build("/test/Test1Activity").navigation();

    }
}
