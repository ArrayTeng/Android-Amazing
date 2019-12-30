package com.example.handlerdemo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {




    @Test
    public void addition_isCorrect() {
       new Thread(new Runnable() {
           @Override
           public void run() {
               Looper.prepare();
               Handler handler = new Handler(new Handler.Callback() {
                   @Override
                   public boolean handleMessage(Message msg) {
                       System.out.println((String) msg.obj);
                       return false;
                   }
               });
               Message message01 = Message.obtain();
               Message message02 = Message.obtain();
               Message message03 = Message.obtain();
               Message message04 = Message.obtain();
               message01.obj = "1";
               message02.obj = "2";
               message03.obj = "3";
               message04.obj = "4";
               handler.sendMessage(message01);
               handler.sendMessage(message02);
               handler.sendMessageDelayed(message03,2000L);
               handler.sendMessageDelayed(message04,500L);
               Looper.loop();
           }
       }).start();
    }
}