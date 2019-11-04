package com.example.routercore;

import com.example.routerannotation.entity.RouteMeta;

import java.util.Map;

/**
 * @author tengfei
 * date 2019-11-04 17:52
 * email tengfeigo@outlook.com
 * description
 */
public interface IRouteGroup {

    void loadInto(Map<String, RouteMeta> atlas);
}
