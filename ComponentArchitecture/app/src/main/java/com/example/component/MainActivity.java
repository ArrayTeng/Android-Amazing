package com.example.component;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.routerannotation.Route;

/**
 * @author tengfei
 * date 2019-11-01 20:26
 * email tengfeigo@outlook.com
 * description
 */

@Route()
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
