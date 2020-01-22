package com.example.mvp.mvp;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @author tengfei
 * date 2019-12-10 21:13
 * email arrayadapter.cn@gmail.com
 * description
 */
public class BasePresenter<V extends BaseView, M extends BaseModel> {

    private WeakReference<V> mViewReference;
    private V mProxyView;

    private M mModel;

    @SuppressWarnings("unchecked")
    public void attach(V view) {
        this.mViewReference = new WeakReference<>(view);
        mProxyView = (V) Proxy.newProxyInstance(mViewReference.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (null == mViewReference || null == mViewReference.get()) {
                    return null;
                }
                return method.invoke(mViewReference, args);
            }
        });

        Type[] params = ((ParameterizedType) Objects.requireNonNull(this.
                getClass().getGenericSuperclass())).getActualTypeArguments();

        try {

            mModel = (M) ((Class) params[1]).newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void detach() {
        this.mViewReference.clear();
        this.mViewReference = null;
    }

    public V proxyView() {
        return mProxyView;
    }

    public M model() {
        return mModel;
    }
}
