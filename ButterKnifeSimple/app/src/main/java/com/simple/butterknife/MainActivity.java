package com.simple.butterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.butterknife.R;
import com.example.butterknife.annotations.BindView;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
