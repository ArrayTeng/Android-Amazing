// IOnNewBookArrivedListener.aidl
package com.example.tengfei.androidcrossprocess;
import com.example.tengfei.androidcrossprocess.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrivedListener(in Book book);
}
