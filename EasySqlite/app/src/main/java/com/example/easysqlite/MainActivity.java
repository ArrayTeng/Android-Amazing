package com.example.easysqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.easysqlite.bean.Personal;
import com.example.easysqlite.bean.User;
import com.example.easysqlite.dao.UserDao;
import com.example.easysqlite.sql.BaseDao;
import com.example.easysqlite.sql.BaseDaoFactory;
import com.example.easysqlite.sql.IBaseDao;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private UserDao dao;

    private static int loginTag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission(this);

        dao = BaseDaoFactory.getInstance().allUserDao(UserDao.class, User.class);
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
        loginTag++;
        dao.insert(new User(loginTag,"登录","庞菲菲  "+loginTag,27));
        //dao.insert(new User(1,"登录","tengfei",18));
        //dao.insert(new User(1,"登录","xiaoming",25));
    }

    public void update(View view) {

        User user = new User();
        user.name = "xiaoming";

        dao.update(new User(1,"登录","滕大肥",25),user);

    }

    public void delete(View view) {

        dao.delete(new User(1,"登录","滕大肥",25));
    }

    public void query(View view) {

        List<User> list = dao.query(new User());

        Log.e("tmd",list.size()+"");
    }

    public void personalDb(View view) {
        BaseDao<Personal> personalDao = BaseDaoFactory.getInstance().personalDao(BaseDao.class, Personal.class);

        Personal personal = new Personal("MessageMessageMessageMessage");

        personalDao.insert(personal);
    }
}