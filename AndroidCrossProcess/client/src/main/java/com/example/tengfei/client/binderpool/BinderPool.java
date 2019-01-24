package com.example.tengfei.client.binderpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.tengfei.client.IBinderPool;

import java.util.concurrent.CountDownLatch;

public class BinderPool {

    public static final int PASSWORD_MANAGER_IMPL_CODE = 0x001;
    public static final int INFO_MANAGER_IMPL_CODE = 0x002;

    private static volatile BinderPool sInstance;
    private Context context;

    private IBinderPool iBinderPool;
    private CountDownLatch mConnectBinderPoolDownLatch;

    private BinderPool(Context context) {
        this.context = context;
        connectionBinderPoolService();
    }

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            iBinderPool.asBinder().unlinkToDeath(deathRecipient, 0);
            iBinderPool = null;
            connectionBinderPoolService();
        }
    };

    private ServiceConnection binderPoolServiceConnnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinderPool = IBinderPool.Stub.asInterface(service);
            try {
                iBinderPool.asBinder().linkToDeath(deathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mConnectBinderPoolDownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void connectionBinderPoolService() {
        mConnectBinderPoolDownLatch = new CountDownLatch(1);
        Intent intent = new Intent(context, BinderPoolService.class);
        context.bindService(intent, binderPoolServiceConnnection, Context.BIND_AUTO_CREATE);
        try {
            mConnectBinderPoolDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static BinderPool getInstance(Context context) {
        if (sInstance == null) {
            synchronized (BinderPool.class) {
                if (sInstance == null) {
                    sInstance = new BinderPool(context);
                }
            }
        }
        return sInstance;
    }

    public synchronized IBinder queryBinder(int code) {
        IBinder binder = null;
        if (iBinderPool != null) {
            try {
                binder = iBinderPool.queryBinder(code);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return binder;
    }

}
