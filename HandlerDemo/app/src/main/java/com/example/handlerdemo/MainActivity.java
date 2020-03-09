package com.example.handlerdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity_TAG";

    private int syncToken;

    private Handler handler;

    public static final int MESSAGE_TYPE_SYNC = 1;
    public static final int MESSAGE_TYPE_ASYN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initHandler();
    }

    private void initHandler() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == MESSAGE_TYPE_SYNC) {
                            Log.e(TAG, "收到普通消息");
                        } else if (msg.what == MESSAGE_TYPE_ASYN) {
                            Log.e(TAG, "收到异步消息");
                        }
                    }
                };
                Looper.loop();
            }
        }).start();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void postSyncBarrier(View view) {
        MessageQueue messageQueue = handler.getLooper().getQueue();
        try {
            Method method = MessageQueue.class.getDeclaredMethod("postSyncBarrier");
            method.setAccessible(true);
            syncToken = (int) method.invoke(messageQueue);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void removeSyncBarrier(View view) {
        MessageQueue messageQueue = handler.getLooper().getQueue();
        try {
            Method method = MessageQueue.class.getDeclaredMethod("removeSyncBarrier", int.class);
            method.setAccessible(true);
            method.invoke(messageQueue, syncToken);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public void sendSyncMessage(View view) {
        Log.e(TAG, "插入普通消息");
        Message message = Message.obtain();
        message.what = MESSAGE_TYPE_SYNC;
        handler.sendMessageDelayed(message, 1000);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public void sendAsyncMessage(View view) {
        Log.e(TAG, "插入异步消息");
        Message message = Message.obtain();
        message.what = MESSAGE_TYPE_ASYN;
        message.setAsynchronous(true);
        handler.sendMessageDelayed(message, 1000);
    }


}
