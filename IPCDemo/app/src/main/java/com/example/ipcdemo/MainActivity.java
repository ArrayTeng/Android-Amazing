package com.example.ipcdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity_TAG";

    private UserAIDL userAIDL;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            userAIDL = UserAIDL.Stub.asInterface(service);

            try {
                Log.e(TAG, "onServiceConnected: "+userAIDL.getUserName()+"   "+userAIDL.getUserPassWord() );
                userAIDL.getUserPassWord();
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
        Intent intent = new Intent();
        intent.setAction("com.tengfei.messageservice.action");
        intent.setPackage("com.example.ipcdemo");
        startService(intent);

    }

    public void startServiceClick(View view) {
        Intent intent = new Intent();
        intent.setAction("com.tengfei.messageservice.action");
        intent.setPackage("com.example.ipcdemo");

        bindService(intent,connection, Context.BIND_AUTO_CREATE);

    }
}
