package com.example.tengfei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.butterknife.annotations.BindView;
import com.example.butterknife.simple.ButterKnife;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        textView.setText("Hello World");
    }
}
