package com.example.moudlea;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.component.R;


/**
 * @author tengfei
 * date 2019/4/5 2:28 PM
 * email tengfeigo@outlook.com
 * description
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void fromAtoB(View view) {
        try {
            Class mClass = Class.forName("com.example.moudleb.MoudleBActivity");
            Intent intent = new Intent(this, mClass);
            intent.putExtra("MoudleB","MoudleB");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void fromAtoMain(View view) {

        try {
            Class mClass = Class.forName("com.example.component.MainActivity");
            Intent intent = new Intent(this, mClass);
            intent.putExtra("MoudleB","MoudleB");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
