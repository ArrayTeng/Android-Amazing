package com.example.easysqlite.bean;

import com.example.easysqlite.sql.annotion.DbFiled;
import com.example.easysqlite.sql.annotion.DbTable;

@DbTable("table_personal")
public class Personal {

    @DbFiled("table_message")
    String message;

    public Personal() {
    }

    public Personal(String message) {
        this.message = message;
    }
}
