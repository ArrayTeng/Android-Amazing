package com.example.tengfei.androidcrossprocess.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.tengfei.androidcrossprocess.R;

/**
 * @author tengfei
 */
public class MessengerActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MessengerActivity";

    private Messenger messenger;

    private Messenger getServiceMessageMessenger = new Messenger(new MessengerHandler());


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            Message message = Message.obtain(null, 0);
            Bundle data = new Bundle();
            data.putString("msg", "hello this is client");
            message.setData(data);
            message.replyTo = getServiceMessageMessenger;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        findViewById(R.id.bt_messenger).setOnClickListener(this);
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_messenger:

                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.i(TAG, "receive msg from service" + msg.getData().getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }
}
