package com.example.tengfei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.annotations.BindView;
import com.example.butterknife.ButterKnifeUtils;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnifeUtils.init(this);

        textView.setText("hahahha");

    }
}
