package com.amazing.tengfei.routerannotation.model;

import com.amazing.tengfei.routerannotation.ARouter;
import com.amazing.tengfei.routerannotation.enums.RouteType;

import javax.lang.model.element.Element;

/**
 * @author 飞一般的感觉
 * date 2020/5/8 9:28 AM
 * email arrayadapter.cn@gmail.com
 * description 路由节点
 */
public class RouteMeta {

    private RouteType routeType;

    private Element element;

    private String path;

    private String group;

    private Class<?> destination;

    public static RouteMeta build(RouteType type, Class<?> destination, String path, String
            group) {
        return new RouteMeta(type, null, destination, path, group);
    }

    public RouteMeta() {
    }

    public RouteMeta(RouteType type, ARouter route, Element element) {
        this(type, element, null, route.path(), route.group());
    }

    public RouteMeta(RouteType type, Element element, Class<?> destination, String path, String
            group) {
        this.routeType = type;
        this.destination = destination;
        this.element = element;
        this.path = path;
        this.group = group;
    }


    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Class<?> getDestination() {
        return destination;
    }

    public void setDestination(Class<?> destination) {
        this.destination = destination;
    }
}
