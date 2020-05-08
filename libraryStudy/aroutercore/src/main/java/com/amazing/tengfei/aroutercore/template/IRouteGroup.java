package com.amazing.tengfei.aroutercore.template;

import com.amazing.tengfei.routerannotation.model.RouteMeta;

import java.util.Map;

/**
 * @author 飞一般的感觉
 * date 2020/5/8 9:25 AM
 * email arrayadapter.cn@gmail.com
 * description
 */
public interface IRouteGroup {

    void loadInto(Map<String, RouteMeta> atlas);
}
