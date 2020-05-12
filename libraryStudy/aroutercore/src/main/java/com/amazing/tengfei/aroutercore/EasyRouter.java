package com.amazing.tengfei.aroutercore;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import com.amazing.tengfei.aroutercore.template.IRouteGroup;
import com.amazing.tengfei.aroutercore.template.IRouteRoot;
import com.amazing.tengfei.aroutercore.utils.ClassUtils;
import com.amazing.tengfei.routerannotation.model.RouteMeta;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import androidx.core.app.ActivityCompat;

/**
 * @author 飞一般的感觉
 * date 2020/5/8 10:45 PM
 * email arrayadapter.cn@gmail.com
 * description
 */
public class EasyRouter {

    private static final String TAG = "EasyRouter_TAG";

    private static final String SDK_ROUTER_PACKAGE = "com.amazing.tengfei.aroutercore";

    private static final String ROUTER_ROOT = "com.amazing.tengfei.aroutercore.ARouter$$Root";

    @SuppressLint("StaticFieldLeak")
    private static volatile EasyRouter easyRouter;

    private Application mContext;

    private EasyRouter() {

    }

    public static EasyRouter getInstance() {
        if (easyRouter == null) {
            synchronized (EasyRouter.class) {
                if (easyRouter == null) {
                    easyRouter = new EasyRouter();
                }
            }
        }
        return easyRouter;
    }

    public void init(Application context) {
        mContext = context;
        loadInfo();
    }


    /**
     * 将分组信息映射到内存中
     */
    private void loadInfo() {
        try {
            //获取包名为 com.amazing.tengfei.aroutercore 的所有类
            Set<String> routerSet = ClassUtils.getFileNameByPackageName(mContext, SDK_ROUTER_PACKAGE);
            //遍历获取 root 下的分组信息建立分组信息
            for (String className : routerSet) {
                if (className.startsWith(ROUTER_ROOT)) {
                    //获取 root 路由表,将模块下的分组 root-group 信息添加到 Warehouse.groupMap 中
                    ((IRouteRoot) Class.forName(className).getConstructor().newInstance()).loadInto(Warehouse.groupMap);
                }
            }

            for (Map.Entry<String, Class<? extends IRouteGroup>> entry : Warehouse.groupMap.entrySet()) {
                Log.e(TAG, "loadInfo: key" + entry.getKey() + "   value   " + entry.getValue());
            }
        } catch (PackageManager.NameNotFoundException | InterruptedException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public Postcard build(String path) {
        if (TextUtils.isEmpty(path)) {
            throw new RuntimeException("路由地址无效");
        }
        return build(path, extractGroup(path));
    }

    public Postcard build(String path, String group) {
        return new Postcard(path, group);
    }

    private String extractGroup(String path) {
        if (TextUtils.isEmpty(path) || !path.startsWith("/")) {
            throw new RuntimeException(path + " : 不能提取group.");
        }
        try {
            String defaultGroup = path.substring(1, path.indexOf("/", 1));
            if (TextUtils.isEmpty(defaultGroup)) {
                throw new RuntimeException(path + " : 不能提取group.");
            } else {
                return defaultGroup;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void navigation(Postcard postcard) {
        _navigation(postcard);
    }

    private void _navigation(Postcard postcard) {

        prepareCard(postcard);

        switch (postcard.getRouteType()) {
            case ACTIVITY:
                Intent intent = new Intent(mContext, postcard.getDestination());
                ActivityCompat.startActivity(mContext, intent, null);
                break;
            default:
                break;
        }
    }

    private void prepareCard(Postcard postcard) {
        Map<String, Class<? extends IRouteGroup>> rootMap = Warehouse.groupMap;
        Class<? extends IRouteGroup> groupClass = rootMap.get(postcard.getGroup());

        Map<String, RouteMeta> groupMap = new HashMap<>();

        try {
            groupClass.newInstance().loadInto(groupMap);

            RouteMeta routeMeta = groupMap.get(postcard.getPath());
            postcard.setDestination(routeMeta.getDestination());
            postcard.setRouteType(routeMeta.getRouteType());

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}
