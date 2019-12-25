package com.example.handlerdemo.handler;

/**
 * @author tengfei
 * date 2019-12-25 23:28
 * email arrayadapter.cn@gmail.com
 * description
 */
public final class MessageQueue {


    private Message mMessages;

    /**
     * enqueueMessage 方法的唯一作用就是将message消息塞到消息队列中并按时间排序
     */
    public boolean enqueueMessage(Message msg, long when) {
        if (msg.target == null) {
            throw new IllegalArgumentException("Message must have a target.");
        }

        synchronized (this) {
            msg.when = when;
            Message p = mMessages;

            if (p == null || when == 0 || when < p.when) {
                msg.next = p;
                mMessages = msg;
            } else {
                Message prev;
                for (; ; ) {
                    prev = p;
                    p = p.next;
                    if (p == null || when < p.when) {
                        break;
                    }
                }
                msg.next = p;
                prev.next = msg;
            }
        }
        return false;
    }
}
