package com.example.tengfei.client.binderpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.tengfei.client.IInFoManager;
import com.example.tengfei.client.IPassWordManager;
import com.example.tengfei.client.Info;
import com.example.tengfei.client.R;

import java.util.List;

/**
 * @author tengfei
 */
public class BinderPoolActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "BinderPoolActivity";


    private IInFoManager iInFoManager;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iInFoManager = IInFoManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_binder_pool);
        findViewById(R.id.bt_addInformation).setOnClickListener(this);
        findViewById(R.id.bt_getInformationList).setOnClickListener(this);
        findViewById(R.id.bt_encryption).setOnClickListener(this);
        findViewById(R.id.bt_decrypt).setOnClickListener(this);
        Intent intent = new Intent(this, BinderPoolService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_addInformation:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "bt_addInformation");
                        BinderPool mBinderPool = BinderPool.getInstance(BinderPoolActivity.this);
                        IBinder binder = mBinderPool.queryBinder(BinderPool.INFO_MANAGER_IMPL_CODE);
                        //将服务端返回的 Binder 对象转换为客户端需要的 AIDL 接口类型的对象
                        IInFoManager inFoManager = IInFoManager.Stub.asInterface(binder);
                        try {
                            inFoManager.addInformation(new Info(0x001, "#info 01"));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


                break;
            case R.id.bt_getInformationList:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BinderPool mBinderPool = BinderPool.getInstance(BinderPoolActivity.this);
                        IBinder getInfoBinder = mBinderPool.queryBinder(BinderPool.INFO_MANAGER_IMPL_CODE);
                        //将服务端返回的 Binder 对象转换为客户端需要的 AIDL 接口类型的对象
                        IInFoManager getInfoManager = IInFoManager.Stub.asInterface(getInfoBinder);
                        try {
                            List<Info> infoList = getInfoManager.getInformationList();
                            for (Info info : infoList) {
                                Log.i(TAG, " #id : " + info.id + " #info : " + info.infomation);
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
            case R.id.bt_encryption:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BinderPool mBinderPool = BinderPool.getInstance(BinderPoolActivity.this);
                        IBinder encryptionBinder = mBinderPool.queryBinder(BinderPool.PASSWORD_MANAGER_IMPL_CODE);
                        IPassWordManager iPassWordManager = IPassWordManager.Stub.asInterface(encryptionBinder);
                        try {
                            String passWord = iPassWordManager.encryption("PassWord");
                            Log.i(TAG, passWord);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
            case R.id.bt_decrypt:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BinderPool mBinderPool = BinderPool.getInstance(BinderPoolActivity.this);
                        IBinder decryptBinder = mBinderPool.queryBinder(BinderPool.PASSWORD_MANAGER_IMPL_CODE);
                        IPassWordManager iPassWordManagerDecrypt = IPassWordManager.Stub.asInterface(decryptBinder);
                        try {
                            String passWord = iPassWordManagerDecrypt.encryption("PassWord");
                            Log.i(TAG, passWord);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
            default:
                break;
        }
    }
}
