package com.example.ipclib;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.example.ipclib.bean.RequestBean;
import com.example.ipclib.bean.RequestParameter;
import com.example.ipclib.cache.CacheCenter;
import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 具体的服务 - 总服务
 */
public class EasyServiceManager extends Service {

    private final Gson gson = new Gson();

    private final CacheCenter cacheCenter = CacheCenter.getInstance();

    //服务获取 实例化对象

    public static final int SERVICE_GET = 1;

    //服务调用
    public static final int SERVICE_INVOKE = 2;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new EasyBinderInterface.Stub() {
            @Override
            public String request(String request) throws RemoteException {
                RequestBean requestBean = gson.fromJson(request,RequestBean.class);
                int type = requestBean.getType();

                switch (type){
                    case SERVICE_GET:
                        Method method = cacheCenter.getMethod(requestBean);
                        if(method!=null){
                            try {
                                method.invoke(null,makeParameterObject(requestBean));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case SERVICE_INVOKE:

                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + type);
                }
                return null;
            }
        };
    }

    private Object[] makeParameterObject(RequestBean requestBean) {

        Object[] mParameters = null;
        RequestParameter[] requestParameters = requestBean.getRequestParameters();
        if (requestParameters != null && requestParameters.length > 0) {
            mParameters = new Object[requestBean.getRequestParameters().length];
            for (int i = 0; i < requestParameters.length; i++) {
                RequestParameter requestParameter = requestParameters[i];
                Class<?> clazz = cacheCenter.getClassType(requestParameter.getParameterClassName());
//                    clazz   value  object
                mParameters[i] = gson.fromJson(requestParameter.getParameterValue(), clazz);
            }

        }else {
            mParameters = new Object[0];
        }
        return mParameters;
    }

}
