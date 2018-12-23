package com.example.tengfei.contentproviderdemo;

import android.app.slice.Slice;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * @author tengfei ContentProvider 主要以表格的形式来组织数据，这一点和数据库差不多
 */
public class BookProvider extends ContentProvider {

    private static final String TAG = "BookProvider";

    private static final String AUTHORITIES = "com.example.tengfei.contentproviderdemo.BookProvider";

    /**
     * 如果需要让外界知道访问的是哪一个表，那么需要为它们定义 Uri 和 UriCode ，通过 UriMatcher 来将两者关联起来，
     * 当外界通过 Uri 来访问 ContentProvider 的时候就可以获取对应的 UriCode，通过 UriCode 可以知道访问的是哪一个表。
     */

    private static final int BOOK_CONTENT_CODE = 0x000;
    private static final int USER_CONTENT_CODE = 0x001;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITIES, "book", BOOK_CONTENT_CODE);
        URI_MATCHER.addURI(AUTHORITIES, "user", USER_CONTENT_CODE);
    }

    private Context mContext;
    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {
        Log.i(TAG, "onCreate # " + Thread.currentThread().getName());
        mContext = getContext();
        mDb = new DbOpenHelper(mContext).getWritableDatabase();
        initProviderData();
        return true;
    }

    private void initProviderData() {
        // TODO: 2018/12/23 不推荐在主线程中执行耗时操作
    }


    /**
     * 用来返回一个 Uri 请求所对应的 MIME（媒体） 类型
     */
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        Log.i(TAG, "insert # " + Thread.currentThread().getName());
        String tabName = getTableName(uri);
        if (tabName == null) {
            throw new IllegalArgumentException("UnSupported URI" + uri);
        }
        mDb.insert(tabName, null, values);
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        Log.i(TAG, "delete # " + Thread.currentThread().getName());
        String tabName = getTableName(uri);
        if (tabName == null) {
            throw new IllegalArgumentException("UnSupported URI" + uri);
        }
        int count = mDb.delete(tabName, selection, selectionArgs);
        if (count > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.i(TAG, "update # " + Thread.currentThread().getName());
        String tabName = getTableName(uri);
        if (tabName == null) {
            throw new IllegalArgumentException("UnSupported URI" + uri);
        }
        int row = mDb.update(tabName, values, selection, selectionArgs);
        if (row > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return row;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.i(TAG, "query # " + Thread.currentThread().getName());
        String tabName = getTableName(uri);
        if (tabName == null) {
            throw new IllegalArgumentException("UnSupported URI" + uri);
        }
        return mDb.query(tabName, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    private String getTableName(Uri uri) {
        String tabName = null;
        switch (URI_MATCHER.match(uri)) {
            case BOOK_CONTENT_CODE:
                tabName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_CONTENT_CODE:
                tabName = DbOpenHelper.USER_TABLE_NAME;
                break;
            default:
                break;
        }
        return tabName;
    }
}
