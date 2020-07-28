package com.tengfei.anative;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import io.flutter.embedding.android.FlutterActivity;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {

        startActivity(
                FlutterActivity
                        .withCachedEngine("MY_CACHED_ENGINE_ID")
                        .build(MainActivity.this));
    }

    public void overrideActivityClick(View view) {
        FlutterAppActivity.startFlutterAppActivity(this,"Hello");
    }
}