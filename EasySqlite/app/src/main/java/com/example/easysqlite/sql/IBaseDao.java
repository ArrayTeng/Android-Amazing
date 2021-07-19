package com.example.easysqlite.sql;

import java.util.List;

public interface IBaseDao<T> {

    Long insert(T entity);

    int update(T entity, T where);

    int delete(T where);

    List<T> query(T where);


}
