package com.example.moudleb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author tengfei
 * date 2019/4/10 11:23 AM
 * email tengfeigo@outlook.com
 * description
 */
public class MoudleBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moudleb_activity_moudleb);
        Intent intent = getIntent();
        String text = intent.getStringExtra("MoudleB");
        if (text!=null){
            Log.i("MoudleBActivity"," text: "+text);
        }
    }
}
