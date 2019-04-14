package com.example.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author tengfei
 * date 2019/3/21 9:26 AM
 * email tengfeigo@outlook.com
 * description
 */
public class MainActivity extends AppCompatActivity {

    private static String SHIMINYUN_PACKAGE_NAME = "mobi.w3studio.apps.android.shsmy.phone";
    private static String SHIMINYUN_CLASS_NAME = "mobi.w3studio.apps.android.shsmy.phone.ui.ThirdPartyCallActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    public void click(View view) {
        Intent mIntent = new Intent();
        mIntent.setClassName(SHIMINYUN_PACKAGE_NAME, SHIMINYUN_CLASS_NAME);
        //mIntent.putExtras(bundle);
        //mIntent.addFlags(268435456);
        startActivity(mIntent);
    }
}
