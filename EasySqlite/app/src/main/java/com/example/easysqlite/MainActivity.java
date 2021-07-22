package com.example.easysqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.easysqlite.bean.User;
import com.example.easysqlite.sql.BaseDao;
import com.example.easysqlite.sql.BaseDaoFactory;
import com.example.easysqlite.sql.IBaseDao;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private IBaseDao<User> iBaseDao;

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

    public void insert(View view) {
        iBaseDao.insert(new User("滕飞",27));
        iBaseDao.insert(new User("tengfei",18));
        iBaseDao.insert(new User("xiaoming",25));
    }

    public void update(View view) {

        User user = new User();
        user.name = "xiaoming";

        iBaseDao.update(new User("滕大肥",25),user);

    }

    public void delete(View view) {

        iBaseDao.delete(new User("滕大肥",25));
    }

    public void query(View view) {

        List<User> list = iBaseDao.query(new User());

        Log.e("tmd",list.size()+"");
    }
}