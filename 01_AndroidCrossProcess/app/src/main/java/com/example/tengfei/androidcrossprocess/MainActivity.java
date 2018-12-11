package com.example.tengfei.androidcrossprocess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tengfei.androidcrossprocess.aidldemo.AIDLActivity;
import com.example.tengfei.androidcrossprocess.blog.FirstActivity;
import com.example.tengfei.androidcrossprocess.messenger.MessengerActivity;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_main_messenger).setOnClickListener(this);
        findViewById(R.id.bt_main_aidl).setOnClickListener(this);
        findViewById(R.id.bt_main_blog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_main_messenger:
                startActivity(MessengerActivity.class);
                break;
            case R.id.bt_main_aidl:
                startActivity(AIDLActivity.class);
                break;
            case R.id.bt_main_blog:
                startActivity(FirstActivity.class);
                break;
            default:
                break;
        }
    }

    private <T> void startActivity(Class<T> z) {
        Intent intent = new Intent(MainActivity.this, z);
        startActivity(intent);
    }
}
