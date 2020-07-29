package com.tengfei.anative;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_text);
    }

    public void GoClick(View view) {
        String param = editText.getText().toString();
        FlutterAppActivity.startFlutterAppActivity(this,param);
    }
}