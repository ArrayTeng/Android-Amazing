package com.example.tengfei.server;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author tengfei
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_start_service).setOnClickListener(this);
        findViewById(R.id.bt_bind_service).setOnClickListener(this);
        findViewById(R.id.bt_stop_service).setOnClickListener(this);
        findViewById(R.id.bt_unbind_service).setOnClickListener(this);
        findViewById(R.id.bt_start_intent_service).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, TestService.class);
        switch (v.getId()) {
            case R.id.bt_start_service:
                startService(intent);
                break;
            case R.id.bt_bind_service:
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.bt_stop_service:
                stopService(intent);
                break;
            case R.id.bt_unbind_service:
                unbindService(connection);
                break;
            case R.id.bt_start_intent_service:
                Intent myIntent = new Intent(this,MyIntentService.class);
                startService(myIntent);
                break;
            default:
                break;
        }
    }
}
