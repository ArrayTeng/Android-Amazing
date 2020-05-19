package com.amazing.tengfei.librarystudy.touch;

import android.app.IntentService;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.amazing.tengfei.librarystudy.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 飞一般的感觉
 * date 2020/5/19 10:37 AM
 * email arrayadapter.cn@gmail.com
 * description
 */
public class TouchActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);

        HandlerThread handlerThread = new HandlerThread("");
        handlerThread.start();


    }
}
