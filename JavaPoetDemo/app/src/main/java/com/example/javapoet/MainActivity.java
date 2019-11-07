package com.example.javapoet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.annotation.ContentView;
import com.example.core.ViewBind;


/**
 * @author tengfei
 */

@ContentView(id = R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewBind.bind(this);
    }


}
