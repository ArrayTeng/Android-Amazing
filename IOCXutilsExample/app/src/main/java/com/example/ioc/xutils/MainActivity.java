package com.example.ioc.xutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ioc.xutils.annotion.BindView;
import com.example.ioc.xutils.annotion.ContentView;
import com.example.ioc.xutils.annotion.OnClick;

/**
 * @author tengfei
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity{

    @BindView(R.id.main_text)
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtils.inJectContentView(this);
        InjectUtils.inJectView(this);
        InjectUtils.inJectClick(this);
        mTextView.setText("Hello World");
    }

    @OnClick(R.id.main_text)
    public void click(View view){
        Toast.makeText(this,"fasdfdas",Toast.LENGTH_SHORT).show();
    }
}
