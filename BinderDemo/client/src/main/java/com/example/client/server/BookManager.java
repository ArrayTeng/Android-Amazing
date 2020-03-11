package com.example.client.server;

import android.os.IInterface;
import android.os.RemoteException;

import com.example.client.Book;

import java.util.List;

/**
 * @author tengfei
 * date 2020-03-11 12:29
 * email arrayadapter.cn@gmail.com
 * description 定义一个接口继承 android.os.IInterface 来表示服务端提供的能力
 */
public interface BookManager extends IInterface {

    void addBook(Book book) throws RemoteException;

    List<Book> getBooks() throws RemoteException;
}
