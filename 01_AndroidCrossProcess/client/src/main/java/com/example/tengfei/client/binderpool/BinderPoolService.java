package com.example.tengfei.client.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.tengfei.client.IBinderPool;
import com.example.tengfei.client.Info;

import java.util.concurrent.CopyOnWriteArrayList;

import static com.example.tengfei.client.binderpool.BinderPool.INFO_MANAGER_IMPL_CODE;
import static com.example.tengfei.client.binderpool.BinderPool.PASSWORD_MANAGER_IMPL_CODE;


/**
 * @author tengfei
 */
public class BinderPoolService extends Service {

//    private IBinder iBinder = new BinderPool.BinderPoolImpl();

    private CopyOnWriteArrayList<Info> infoCopyOnWriteArrayList = new CopyOnWriteArrayList<>();

    private IBinder iBinder = new IBinderPool.Stub() {
        @Override
        public IBinder queryBinder(int code) throws RemoteException {
            switch (code) {
                case PASSWORD_MANAGER_IMPL_CODE:
                    return new PassWordManagerImpl();
                case INFO_MANAGER_IMPL_CODE:
                    return new InfoManagerImpl(infoCopyOnWriteArrayList);
                default:
                    break;
            }
            return new PassWordManagerImpl();
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
}
