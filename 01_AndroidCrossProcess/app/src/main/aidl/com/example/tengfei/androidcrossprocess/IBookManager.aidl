// IBookManager.aidl
package com.example.tengfei.androidcrossprocess;

import com.example.tengfei.androidcrossprocess.Book;
import com.example.tengfei.androidcrossprocess.IOnNewBookArrivedListener;

interface IBookManager {

    List<Book> getBookList();

    void addBook(in Book book);

    void registerListener(IOnNewBookArrivedListener listener);

    void unregisterListener(IOnNewBookArrivedListener listener);
}
