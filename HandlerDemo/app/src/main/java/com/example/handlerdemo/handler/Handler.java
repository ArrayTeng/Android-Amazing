package com.example.handlerdemo.handler;


import android.os.SystemClock;
import android.util.Log;

/**
 * @author tengfei
 * date 2019-12-25 23:17
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Handler {


    public final boolean sendMessage(Message msg) {
        return sendMessageDelayed(msg, 0);
    }

    private boolean sendMessageDelayed(Message msg, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return sendMessageAtTime(msg, SystemClock.uptimeMillis() + delayMillis);
    }

    private boolean sendMessageAtTime(Message msg, long uptimeMillis) {
        // TODO: 2019-12-25 为了方便测试先这么写
        MessageQueue queue = new MessageQueue();
        if (queue == null) {
            RuntimeException e = new RuntimeException(
                    this + " sendMessageAtTime() called with no mQueue");
            Log.w("Looper", e.getMessage(), e);
            return false;
        }
        return enqueueMessage(queue, msg, uptimeMillis);
    }

    private boolean enqueueMessage(MessageQueue queue, Message msg, long uptimeMillis) {
        msg.target = this;

        return queue.enqueueMessage(msg, uptimeMillis);
    }
}
