package com.example.tengfei.androidcrossprocess.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.example.tengfei.androidcrossprocess.Book;
import com.example.tengfei.androidcrossprocess.IBookManager;
import com.example.tengfei.androidcrossprocess.IOnNewBookArrivedListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author tengfei
 */
public class BookManagerService extends Service {
    private static final String TAG = "BookManagerService_tag";

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    private RemoteCallbackList<IOnNewBookArrivedListener> bookArrivedListeners = new RemoteCallbackList<>();

    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    private Binder binder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() {
            return mBookList;
        }

        @Override
        public void addBook(Book book) {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) {
            bookArrivedListeners.register(listener);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) {
            bookArrivedListeners.unregister(listener);
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int check = checkCallingOrSelfPermission("com.example.tengfei.androidcrossprocess.aidldemo.BookManagerService");
            if (check == PackageManager.PERMISSION_DENIED) {
                return false;
            }
            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            if (packageName != null && !packageName.startsWith("com.example.tengfei")) {
                return false;
            }
            return super.onTransact(code, data, reply, flags);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(0x001, "基督山恩仇记"));
        mBookList.add(new Book(0x002, "三个火枪手"));
        new Thread(new ServiceWorker()).start();
    }

    @Override
    public void onDestroy() {
        atomicBoolean.set(true);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            while (!atomicBoolean.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + 1;
                Book book = new Book(bookId, "new book #" + bookId);
                onNewBookArrived(book);
            }
        }

        private void onNewBookArrived(Book book) {
            mBookList.add(book);
            final int n = bookArrivedListeners.beginBroadcast();
            for (int i = 0; i < n; i++) {
                try {
                    IOnNewBookArrivedListener iOnNewBookArrivedListener = bookArrivedListeners.getBroadcastItem(i);
                    if (iOnNewBookArrivedListener != null) {
                        iOnNewBookArrivedListener.onNewBookArrivedListener(book);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            bookArrivedListeners.finishBroadcast();
        }
    }

}
