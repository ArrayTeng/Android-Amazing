package com.example.ipcdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

/**
 * @author tengfei
 * date 2020-01-21 10:58
 * email arrayadapter.cn@gmail.com
 * description
 */
public class MessageService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }


    private final UserAIDL.Stub stub = new UserAIDL.Stub() {
        @Override
        public String getUserName() throws RemoteException {
            return "arrayadapter.cn@gmail.com";
        }

        @Override
        public String getUserPassWord() throws RemoteException {
            return "TENGFEI1995";
        }
    };
}
