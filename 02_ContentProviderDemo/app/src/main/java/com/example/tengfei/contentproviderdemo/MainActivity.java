package com.example.tengfei.contentproviderdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.skip_provider_Activity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.skip_provider_Activity:
                skipActivity(ProviderActivity.class);
                break;
            default:
                break;
        }
    }

    private <T> void skipActivity(Class<T> z) {
        Intent intent = new Intent(MainActivity.this, z);
        startActivity(intent);
    }
}
