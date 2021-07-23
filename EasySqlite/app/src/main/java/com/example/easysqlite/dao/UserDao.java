package com.example.easysqlite.dao;

import com.example.easysqlite.bean.User;
import com.example.easysqlite.sql.BaseDao;

import java.util.List;

public class UserDao extends BaseDao<User> {

    @Override
    public Long insert(User entity) {

        List<User> list = query(new User());

        User where;

        for (User user : list) {
            where = new User();
            where.id = user.id;
            user.status = "未登录";
            update(user, where);
        }

        entity.status = "登录";


        return super.insert(entity);
    }

    //当前登录用户
    public User getCurrentUser() {
        User user = new User();
        user.status = "登录";
        List<User> list = query(user);
        if (list.size()>0){
            return list.get(list.size()-1);
        }
        return null;
    }
}
