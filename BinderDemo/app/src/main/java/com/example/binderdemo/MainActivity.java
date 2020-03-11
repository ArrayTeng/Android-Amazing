package com.example.binderdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.util.Log;
import android.view.View;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity_TAG";

    private UserAidl mUserAidl;


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mUserAidl = UserAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this,MessageService.class));

        Intent intent = new Intent();
        intent.setAction("com.example.tengfei.binder");
        intent.setPackage("com.example.binderdemo");
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void getUserName(View view) {
        try {

            Log.e(TAG, "getUserName: "+mUserAidl.getUserName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getPassWord(View view) {
        try {

            Log.e(TAG, "getPassWord: "+ mUserAidl.getUserPwd());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
