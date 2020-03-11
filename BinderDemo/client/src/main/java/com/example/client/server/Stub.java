package com.example.client.server;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import com.example.client.Book;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author tengfei
 * date 2020-03-11 12:38
 * email arrayadapter.cn@gmail.com
 * description  实现一个跨进程调用对象 Stub。Stub 继承 Binder, 说明它是一个 Binder 本地对象；实现 IInterface 接口，
 * 表明具有 Server 承诺给 Client 的能力；
 * Stub 是一个抽象类，具体的 IInterface 的相关实现需要调用方自己实现。
 */
public abstract class Stub extends Binder implements BookManager {

    private static final java.lang.String DESCRIPTOR = "com.example.client.server.BookManager";


    public static final int TRANSACT_ADDBOOK = Binder.FIRST_CALL_TRANSACTION;
    public static final int TRANSACT_GETBOOKS = Binder.FIRST_CALL_TRANSACTION + 1;

    public Stub() {
        this.attachInterface(this, DESCRIPTOR);
    }


    //在客户端绑定服务的时候会调用 asInterface 传入远程服务的 binder 对象，这个函数会检查这个 binder 对象
    //是本地还是远程的binder对象，如果是本地的直接返回，如果是远程的则创建代理类 proxy
    public static BookManager asInterface(android.os.IBinder iBinder) {
        if ((iBinder == null)) {
            return null;
        }
        //调用 queryLocalInterface 去查找 Binder 本地对象，如果找到了就说明 Client 和 Server 在同一进程，
        // 那么这个 binder 本身就是 Binder 本地对象，可以直接使用。
        // 否则说明是 binder 是个远程对象，也就是 BinderProxy。因此需要我们创建一个代理对象 Proxy，通过这个代理对象来是实现远程访问。
        android.os.IInterface iin = iBinder.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof BookManager))) {
            return ((BookManager) iin);
        }
        return new Proxy(iBinder);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code) {

            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;

            case TRANSACT_GETBOOKS:
                data.enforceInterface(DESCRIPTOR);
                List<Book> result = this.getBooks();
                reply.writeNoException();
                reply.writeTypedList(result);
                return true;

            case TRANSACT_ADDBOOK:
                data.enforceInterface(DESCRIPTOR);
                Book arg0 = null;
                if (data.readInt() != 0) {
                    arg0 = Book.CREATOR.createFromParcel(data);
                }
                this.addBook(arg0);
                reply.writeNoException();
                return true;

        }
        return super.onTransact(code, data, reply, flags);
    }
}
