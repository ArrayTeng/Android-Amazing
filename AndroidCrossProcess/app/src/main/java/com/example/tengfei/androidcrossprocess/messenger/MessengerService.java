package com.example.tengfei.androidcrossprocess.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * @author tengfei
 */
public class MessengerService extends Service {

    private static final String TAG = "MessengerService";

    private Messenger messenger = new Messenger(new MessengerHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Log.i(TAG, "receive message from client : " + msg.getData().getString("msg"));
                    Messenger clientMessenger = msg.replyTo;
                    Message replyMessage = Message.obtain(null,1);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply","ok I have received your message");
                    replyMessage.setData(bundle);
                    try {
                        clientMessenger.send(replyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
