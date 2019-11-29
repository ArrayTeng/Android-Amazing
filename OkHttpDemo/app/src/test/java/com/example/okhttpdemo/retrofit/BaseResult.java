package com.example.okhttpdemo.retrofit;

/**
 * @author tengfei
 * date 2019-11-29 11:09
 * email arrayadapter.cn@gmail.com
 * description
 */
public class BaseResult {

    private static final String CODE_STATUS = "200";

    String code;
    String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isOk() {
        return CODE_STATUS.equals(code);
    }

}
