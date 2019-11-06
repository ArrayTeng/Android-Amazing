package com.example.javapoet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.annotation.ContentView;


/**
 * @author tengfei
 */

@ContentView(id = R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity_ViewBind mainActivity_viewBind  = new MainActivity_ViewBind(this);
    }


}
