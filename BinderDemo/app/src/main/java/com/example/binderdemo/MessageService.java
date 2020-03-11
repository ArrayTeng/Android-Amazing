package com.example.binderdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

/**
 * @author tengfei
 * date 2020-03-09 20:49
 * email arrayadapter.cn@gmail.com
 * description
 */
public class MessageService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    private final UserAidl.Stub stub = new UserAidl.Stub() {
        @Override
        public String getUserName() throws RemoteException {
            return "arrayadapter.cn@gmail.com";
        }

        @Override
        public String getUserPwd() throws RemoteException {
            return "tengfei1995";
        }
    };
}
