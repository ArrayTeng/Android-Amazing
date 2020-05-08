package com.amazing.tengfei.routerannotation.enums;

/**
 * @author 飞一般的感觉
 * date 2020/5/8 9:33 AM
 * email arrayadapter.cn@gmail.com
 * description
 */
public enum RouteType {

    ACTIVITY(0, "android.app.Activity");


    int id;
    String className;

    RouteType(int id, String className) {
        this.id = id;
        this.className = className;
    }


}
