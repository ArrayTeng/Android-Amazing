package com.amazing.tengfei.aroutercore;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;

import com.amazing.tengfei.aroutercore.template.IRouteGroup;
import com.amazing.tengfei.aroutercore.template.IRouteRoot;
import com.amazing.tengfei.aroutercore.utils.ClassUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

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
                    ((IRouteRoot)Class.forName(className).getConstructor().newInstance()).loadInto(Warehouse.groupMap);
                }
            }

            for (Map.Entry<String, Class<? extends IRouteGroup>> entry:Warehouse.groupMap.entrySet()){
                Log.e(TAG, "loadInfo: key"+entry.getKey()+"   value   "+entry.getValue());
            }
        } catch (PackageManager.NameNotFoundException | InterruptedException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
