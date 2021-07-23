package com.example.easysqlite.bean;

import com.example.easysqlite.sql.annotion.DbFiled;
import com.example.easysqlite.sql.annotion.DbTable;

@DbTable("tab_user")
public class User {

    @DbFiled("filed_id")
    public Integer id;

    @DbFiled("filed_status")
    public String status;

    @DbFiled("filed_name")
    public String name;

    @DbFiled("filed_age")
    public Integer age;

    public User() {
    }

    public User(Integer id, String status, String name, Integer age) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.age = age;
    }
}
