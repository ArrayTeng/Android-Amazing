package com.example.component;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void skipToMoudleA(View view) {
        try {
            Class mClass = Class.forName("com.example.moudlea.LoginActivity");
            Intent intent = new Intent(MainActivity.this, mClass);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void skipToMoudleB(View view) {
        try {
            Class mClass = Class.forName("com.example.moudleb.MoudleBActivity");
            Intent intent = new Intent(MainActivity.this, mClass);
            intent.putExtra("MoudleB","MoudleB");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
