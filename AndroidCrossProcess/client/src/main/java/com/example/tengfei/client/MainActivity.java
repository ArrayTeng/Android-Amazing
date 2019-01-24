package com.example.tengfei.client;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    private static final String REMOTE_ACTION = "com.example.tengfei.server.MyService";
    private static final String REMOTE_PACKAGE_NAME = "com.example.tengfei.server";

    private static final int REMOTE_MESSAGE_TAG = 0x001;

    private IBindEntity iBindEntity;

    private TextView mTextView;

    private static final String TAG = "MainActivityTag";

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == REMOTE_MESSAGE_TAG) {
                NumberEntity numberEntity = (NumberEntity) msg.obj;
                mTextView.setText(String.valueOf(numberEntity.number));
            }
        }
    };

    private IListener listener = new IListener.Stub() {
        @Override
        public void onNewInFoArrived(NumberEntity numberEntity) {
            Log.i(TAG,"onNewInFoArrived"+numberEntity.number);
            handler.obtainMessage(REMOTE_MESSAGE_TAG, numberEntity).sendToTarget();
        }
    };

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (iBindEntity == null) {
                return;
            }
            //解除死亡代理
            iBindEntity.asBinder().unlinkToDeath(deathRecipient, 0);
            iBindEntity = null;
            Intent intent = new Intent();
            intent.setAction(REMOTE_ACTION);
            intent.setPackage(REMOTE_PACKAGE_NAME);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBindEntity = IBindEntity.Stub.asInterface(service);
            try {
                //设置死亡代理
                service.linkToDeath(deathRecipient, 0);
                Log.i(TAG,"onServiceConnected");
                iBindEntity.registerListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.tv_client);
        findViewById(R.id.bt_client).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(REMOTE_ACTION);
                intent.setPackage(REMOTE_PACKAGE_NAME);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
        });

    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        if (iBindEntity != null && iBindEntity.asBinder().isBinderAlive()) {
            try {
                iBindEntity.registerListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }
}
