package com.example.tengfei.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tengfei.example.activity.ChatActivity;


/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv01).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv01:
                skipActivity(ChatActivity.class);
                break;
            default:
                break;
        }
    }

    private <T> void skipActivity(Class<T> z) {
        Intent intent = new Intent(this, z);
        startActivity(intent);
    }
}
