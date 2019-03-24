package com.example.utils.view;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utils.utils.MyUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tengfei
 * date 2019/3/21 2:30 PM
 * email tengfeigo@outlook.com
 * description
 */
public class AbsNavigationBar implements INavigation {

    private AbstractBuilder mBuilder;

    private View navigationView;

    public AbsNavigationBar(AbstractBuilder builder) {
        this.mBuilder = builder;
        createNavigation();
    }

    /**
     * 创建 NavigationBar
     */
    @Override
    public void createNavigation() {
        //创建 TitleBar 布局对象
        navigationView = LayoutInflater.from(mBuilder.mContext).inflate(mBuilder.layoutId,
                mBuilder.mParent, false);
        //将布局对象添加到父布局中
        attachParent(navigationView, mBuilder.mParent);
        //绑定参数
        attachNavigationParams();
    }

    /**
     * 绑定参数
     */
    @Override
    public void attachNavigationParams() {
        Map<Integer, CharSequence> titleArrayMap = mBuilder.titleArrayMap;
        for (Map.Entry<Integer, CharSequence> entry : titleArrayMap.entrySet()) {
            TextView textView = findViewById(entry.getKey());
            textView.setText(entry.getValue());
        }

        Map<Integer, View.OnClickListener> clickListenerArrayMap = mBuilder.clickListenerArrayMap;
        for (Map.Entry<Integer, View.OnClickListener> entry : clickListenerArrayMap.entrySet()) {
            View view = findViewById(entry.getKey());
            view.setOnClickListener(entry.getValue());
        }
    }

    private <T extends View> T findViewById(Integer key) {
        return (T) navigationView.findViewById(key);
    }

    @Override
    public void attachParent(View navigationView, ViewGroup mParent) {
        mParent.addView(navigationView, 0);
    }

    public AbstractBuilder getBuilder() {
        return mBuilder;
    }

    public static abstract class AbstractBuilder<T extends AbstractBuilder> {

        private Context mContext;
        private ViewGroup mParent;
        private int layoutId;

        private Map<Integer, CharSequence> titleArrayMap;
        private Map<Integer, View.OnClickListener> clickListenerArrayMap;

        public AbstractBuilder(Context mContext, ViewGroup mParent, int layoutId) {
            this.mContext = mContext;
            this.mParent = mParent;
            this.layoutId = layoutId;
            titleArrayMap = new HashMap<>();
            clickListenerArrayMap = new HashMap<>();
        }

        /**
         * 构建 AbsNavigationBar
         *
         * @return AbsNavigationBar
         */
        public abstract AbsNavigationBar builder();

        public T setTitle(int viewId, String text) {
            titleArrayMap.put(viewId, text);
            return MyUtils.cast(this);
        }

        public T setOnClickListener(int viewId, View.OnClickListener clickListener) {
            clickListenerArrayMap.put(viewId, clickListener);
            return MyUtils.cast(this);
        }
    }
}
