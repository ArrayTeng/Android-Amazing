package com.example.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.utils.view.NavigationBar;

/**
 * @author tengfei
 * date 2019/3/21 9:26 AM
 * email tengfeigo@outlook.com
 * description
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = findViewById(R.id.ll);
        new NavigationBar.Builder(this,linearLayout,R.layout.layout_title)
                .setTitle(1,"")
                .builder();
    }
}
