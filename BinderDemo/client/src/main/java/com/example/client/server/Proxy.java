package com.example.client.server;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.example.client.Book;

import java.util.List;

/**
 * @author tengfei
 * date 2020-03-11 12:50
 * email arrayadapter.cn@gmail.com
 * description 参考 https://www.cnblogs.com/a284628487/p/3187320.html
 */
class Proxy implements BookManager {


    private static final java.lang.String DESCRIPTOR = "com.example.client.server.BookManager";

    //远程服务的binder对象
    private IBinder mRemote;

    public Proxy(IBinder iBinder) {
        mRemote = iBinder;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        // data表示的是要传递给远程Binder服务的包裹(Parcel)，远程服务函数所需要的参数必须放入这个包裹中
        Parcel data = Parcel.obtain();
        Parcel replay = Parcel.obtain();

        try {
            //标注远程服务名称
            data.writeInterfaceToken(DESCRIPTOR);
            if (book != null) {
                data.writeInt(1);
                data.writeParcelable(book, 0);
            } else {
                data.writeInt(0);
            }
            mRemote.transact(Stub.TRANSACT_ADDBOOK, data, replay, 0);
            replay.readException();
        } finally {
            data.recycle();
            replay.recycle();
        }
    }

    @Override
    public List<Book> getBooks() throws RemoteException {
        Parcel data = Parcel.obtain();
        Parcel replay = Parcel.obtain();
        List<Book> result;
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            mRemote.transact(Stub.TRANSACT_GETBOOKS, data, replay, 0);
            replay.readException();
            result = replay.createTypedArrayList(Book.CREATOR);
        }finally {
            data.recycle();
            replay.recycle();
        }
        return result;
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }

    public java.lang.String getInterfaceDescriptor() {
        return DESCRIPTOR;
    }

}
