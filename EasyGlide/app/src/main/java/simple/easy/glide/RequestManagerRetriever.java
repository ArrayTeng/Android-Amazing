package simple.easy.glide;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.HashMap;
import java.util.Map;

import simple.easy.glide.binding.ApplicationLifecycle;
import simple.easy.glide.binding.RequestManagerFragment;
import simple.easy.glide.binding.SupportRequestManagerFragment;
import simple.easy.glide.util.Util;

public class RequestManagerRetriever implements Handler.Callback {

    static final String FRAGMENT_TAG = "com.bumptech.glide.manager";

    private static final int ID_REMOVE_FRAGMENT_MANAGER = 1; // android.app Fragment 空白

    private static final int ID_REMOVE_SUPPORT_FRAGMENT_MANAGER = 2; // androidx Fragment 空白

    final Map<android.app.FragmentManager, RequestManagerFragment> pendingRequestManagerFragments = new HashMap<>();


    final Map<androidx.fragment.app.FragmentManager, SupportRequestManagerFragment> pendingSupportRequestManagerFragments =
            new HashMap<>();

    final Handler handler;

    private volatile RequestManager applicationManager;

    public RequestManagerRetriever() {
        handler = new Handler(Looper.getMainLooper(), this);
    }

    /**
     * 适配androidx版本的 FragmentActivity
     */
    public RequestManager get(FragmentActivity fragmentActivity) {
        if (Util.isOnBackgroundThread()) {
            return get(fragmentActivity.getApplicationContext());
        } else {
            //判断当前的Activity不处于destroy中
            Util.assertNotDestroyed(fragmentActivity);
            androidx.fragment.app.FragmentManager fm = fragmentActivity.getSupportFragmentManager();
            return supportFragmentGet(fragmentActivity, fm);
        }

    }

    /**
     * 适配androidx版本的 fragment
     */
    public RequestManager get(Fragment fragment) {
        if (Util.isOnBackgroundThread()) {
            return get(fragment.getContext().getApplicationContext());
        } else {
            androidx.fragment.app.FragmentManager fm = fragment.getChildFragmentManager();
            return supportFragmentGet(fragment.getContext(), fm);
        }
    }

    /**
     * 适配 android.app.activity
     */
    public RequestManager get(Activity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        } else {
            //判断当前的Activity不处于destroy中
            Util.assertNotDestroyed(activity);
            //通过当前 Activity 获取 FragmentManager
            android.app.FragmentManager fm = activity.getFragmentManager();
            return fragmentGet(activity, fm);
        }
    }

    private RequestManager get(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context 不能为 null");
        } else if (Util.isOnMainThread() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return get((FragmentActivity) context);
            } else if (context instanceof Activity) {
                return get((Activity) context);
            } else if (context instanceof ContextWrapper && ((ContextWrapper) context).getBaseContext().getApplicationContext() != null) {
                return get(((ContextWrapper) context).getBaseContext());
            }

        }
        return getApplicationManager(context);
    }

    private RequestManager getApplicationManager(Context context) {
        if (applicationManager == null) {
            synchronized (this) {
                if (applicationManager == null) {
                    applicationManager = RequestManager.getInstance(new ApplicationLifecycle(), context.getApplicationContext());
                }
            }
        }
        return applicationManager;
    }

    /**
     * 兼容 android.app.Fragment
     */
    private RequestManager fragmentGet(Context context, android.app.FragmentManager fm) {
        //创建 RequestManagerFragment 对象，并在这个 Fragment 中维护一个 RequestManager 对象
        RequestManagerFragment current = getRequestManagerFragment(fm);
        //从 Fragment 中获取 RequestManager
        RequestManager requestManager = current.getRequestManager();

        //首次获取实例化 RequestManager
        if (requestManager == null) {
            //在 RequestManager 的构造函数中传入了 ActivityFragmentLifecycle 对象
            //所以在 RequestManager 中可以感应到生命周期的变化
            requestManager = RequestManager.getInstance(current.getGlideLifecycle(), context);
            current.setRequestManager(requestManager);
        }

        return requestManager;
    }

    /**
     * 创建 RequestManagerFragment
     */
    private RequestManagerFragment getRequestManagerFragment(FragmentManager fm) {
        //尝试通过 findFragmentByTag 的方式来获取 Fragment ，第一次执行到这里的时候返回的值肯定是 null
        RequestManagerFragment current = (RequestManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            //尝试从缓存中获取Fragment对象
            current = pendingRequestManagerFragments.get(fm);
            if (current == null) {
                //创建一个空的Fragment用于管理生命周期
                current = new RequestManagerFragment();
                pendingRequestManagerFragments.put(fm, current);
                //开启一个事务添加一个空的Fragment
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                //发送消息通知移除旧的Fragment
                handler.obtainMessage(ID_REMOVE_FRAGMENT_MANAGER, fm).sendToTarget();
            }
        }
        return current;
    }

    /**
     * 兼容 androidx.fragment.app
     */
    private RequestManager supportFragmentGet(Context context, androidx.fragment.app.FragmentManager fm) {
        SupportRequestManagerFragment current = getSupportRequestManagerFragment(fm);
        RequestManager requestManager = current.getRequestManager();
        if (requestManager == null) {
            requestManager = RequestManager.getInstance(current.getGlideLifecycle(), context);
            current.setRequestManager(requestManager);
        }

        return requestManager;
    }

    private SupportRequestManagerFragment getSupportRequestManagerFragment(androidx.fragment.app.FragmentManager fm) {
        SupportRequestManagerFragment current = (SupportRequestManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = pendingSupportRequestManagerFragments.get(fm);
            if (current == null) {
                current = new SupportRequestManagerFragment();
                pendingSupportRequestManagerFragments.put(fm, current);
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                handler.obtainMessage(ID_REMOVE_SUPPORT_FRAGMENT_MANAGER, fm).sendToTarget();
            }
        }
        return current;
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            case ID_REMOVE_FRAGMENT_MANAGER:
                //兼容 android.app.fragment
                android.app.FragmentManager fm = (FragmentManager) msg.obj;
                pendingRequestManagerFragments.remove(fm);
                break;
            case ID_REMOVE_SUPPORT_FRAGMENT_MANAGER:
                androidx.fragment.app.FragmentManager supportFm = (androidx.fragment.app.FragmentManager) msg.obj;
                pendingSupportRequestManagerFragments.remove(supportFm);
                break;
            default:
                break;
        }
        return false;
    }
}
