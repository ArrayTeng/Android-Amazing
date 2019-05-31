package com.example.ioc.xutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ioc.xutils.annotion.BindView;
import com.example.ioc.xutils.annotion.ContentView;

/**
 * @author tengfei
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_text)
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtils.inJectContentView(this);
        InjectUtils.inJectView(this);
        mTextView.setText("Hello World");
    }
}
