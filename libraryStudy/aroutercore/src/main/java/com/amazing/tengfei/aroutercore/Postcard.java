package com.amazing.tengfei.aroutercore;

import com.amazing.tengfei.routerannotation.model.RouteMeta;

/**
 * @author 飞一般的感觉
 * date 2020/5/9 4:27 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Postcard extends RouteMeta {


    public Postcard(String path, String group) {
        setPath(path);
        setGroup(group);
    }


    public void navigation() {
        EasyRouter.getInstance().navigation(this);
    }
}
