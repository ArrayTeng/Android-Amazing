package com.example.tengfei.androidcrossprocess.blog.binderpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.tengfei.androidcrossprocess.IBinderPool;

import java.util.concurrent.CountDownLatch;


public class BinderPool {

    private static final String TAG = "BinderPool";

    public static final int BINDER_NONE = 0x000;
    public static final int BINDER_COMPUTE = 0x001;
    public static final int BINDER_SECURITY_CENTER = 0x002;

    private Context mContext;
    private IBinderPool mBinderPool;

    private static volatile BinderPool sInstance;
    /**
     * CountDownLatch是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程的操作执行完后再执行
     */
    private CountDownLatch mConnectBinderPoolDownLatch;

    private BinderPool(Context context) {
        mContext = context.getApplicationContext();
        connectionBinderPoolService();
    }

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            mBinderPool.asBinder().unlinkToDeath(deathRecipient, 0);
            mBinderPool = null;
            connectionBinderPoolService();
        }
    };

    private ServiceConnection mBinderPoolServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinderPool = IBinderPool.Stub.asInterface(service);
            try {
                mBinderPool.asBinder().linkToDeath(deathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mConnectBinderPoolDownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private synchronized void connectionBinderPoolService() {
        mConnectBinderPoolDownLatch = new CountDownLatch(1);
        Intent service = new Intent(mContext, BinderPoolService.class);
        mContext.bindService(service, mBinderPoolServiceConnection, Context.BIND_AUTO_CREATE);
        try {
            mConnectBinderPoolDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public IBinder queryBinder(int binderCode) {
        IBinder binder = null;
        if (mBinderPool != null) {
            try {
                binder = mBinderPool.queryBinder(binderCode);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return binder;
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

    public static class BinderPoolImpl extends IBinderPool.Stub {

        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder binder = null;
            switch (binderCode) {
                case BINDER_COMPUTE:
                    binder = new ComputeImpl();
                    break;
                case BINDER_SECURITY_CENTER:
                    binder = new SecurityCenterImpl();
                    break;
                default:
                    break;
            }
            return binder;
        }
    }

}
