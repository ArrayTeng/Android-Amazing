package com.example.easysqlite.bean;

import com.example.easysqlite.sql.annotion.DbFiled;
import com.example.easysqlite.sql.annotion.DbTable;

@DbTable("tab_user")
public class User {

    @DbFiled("filed_name")
    String name;

    @DbFiled("filed_age")
    Integer age;


}
