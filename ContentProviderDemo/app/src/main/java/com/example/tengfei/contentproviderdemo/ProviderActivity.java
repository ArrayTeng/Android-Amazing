package com.example.tengfei.contentproviderdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * @author tengfei
 */
public class ProviderActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ProviderActivity";

    private static final String AUTHORITIES = "com.example.tengfei.contentproviderdemo.BookProvider";
    private static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITIES + "/book");
    private static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITIES + "/user");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_provider);

        findViewById(R.id.bt_provider_insert).setOnClickListener(this);
        findViewById(R.id.bt_provider_delete).setOnClickListener(this);
        findViewById(R.id.bt_provider_update).setOnClickListener(this);
        findViewById(R.id.bt_provider_query).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_provider_insert:
                ContentValues contentValues = new ContentValues();
                contentValues.put("_id", 6);
                contentValues.put("name", "《Android开发艺术探索》");
                getContentResolver().insert(BOOK_CONTENT_URI, contentValues);
                break;
            case R.id.bt_provider_delete:
                break;
            case R.id.bt_provider_update:
                break;
            case R.id.bt_provider_query:
                Cursor cursor = getContentResolver().query(BOOK_CONTENT_URI, new String[]{"_id", "name"}, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        Log.i(TAG, " _id # " + cursor.getInt(0) + " name # " + cursor.getString(1));
                    }
                    cursor.close();
                }
                break;
            default:
                break;

        }
    }
}
