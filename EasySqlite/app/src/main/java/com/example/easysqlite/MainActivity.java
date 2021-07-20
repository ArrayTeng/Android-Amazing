package com.example.easysqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import com.example.easysqlite.bean.User;
import com.example.easysqlite.sql.BaseDao;
import com.example.easysqlite.sql.BaseDaoFactory;
import com.example.easysqlite.sql.IBaseDao;


public class MainActivity extends AppCompatActivity {

    private IBaseDao iBaseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission(this);

        iBaseDao = BaseDaoFactory.getInstance().createBaseDao(BaseDao.class, User.class);
    }

    private boolean checkPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            activity.requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
            },1);
        }

        return false;
    }
}