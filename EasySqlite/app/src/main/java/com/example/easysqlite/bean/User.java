package com.example.easysqlite.bean;

import com.example.easysqlite.sql.annotion.DbFiled;
import com.example.easysqlite.sql.annotion.DbTable;

@DbTable("tab_user")
public class User {

    @DbFiled("filed_name")
    public String name;

    @DbFiled("filed_age")
    public Integer age;

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
