package com.example.easysqlite.enums;

import android.os.Environment;

import com.example.easysqlite.bean.User;
import com.example.easysqlite.dao.UserDao;
import com.example.easysqlite.sql.BaseDaoFactory;

import java.io.File;

public enum PrivateDataBaseEnums {
    dataBase("message.db");

    private String value;

    PrivateDataBaseEnums(String value) {
        this.value = value;
    }

    public String getValue(){
        UserDao userDao = BaseDaoFactory.getInstance().allUserDao(UserDao.class, User.class);

        User currentUser = userDao.getCurrentUser();

        if(currentUser!=null){
            File file = new File(Environment.getExternalStorageDirectory(),"EasySqlite");
            if (!file.exists()){
                file.mkdirs();
            }

            File userFile = new File(file,currentUser.name);

            if (!userFile.exists()){
                userFile.mkdirs();
            }

            return userFile.getAbsolutePath() +"/"+ value;
        }
        return null;
    }
}
