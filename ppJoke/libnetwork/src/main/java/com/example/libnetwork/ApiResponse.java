package com.example.libnetwork;

/**
 * @author tengfei
 * date 2020-02-03 19:07
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ApiResponse<T> {
    public boolean success;
    public int status;
    public String message;
    public T body;
}
