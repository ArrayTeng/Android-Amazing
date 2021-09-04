package simple.easy.glide.binding;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import simple.easy.glide.binding.inter.Lifecycle;
import simple.easy.glide.binding.inter.LifecycleListener;

/**
 * 生命周期回调的管理类，实现了Lifecycle接口。所有的LifecycleListener会添加到一个集合中，
 * 当RequestManagerFragment生命周期方法触发时，会调用ActivityFragmentLifecycle相应生命周期方法，
 * 这个方法然后再遍历调用所有LifecycleListener的生命周期方法
 */
public class ActivityFragmentLifecycle implements Lifecycle {

    private final Set<LifecycleListener> lifecycleListeners =
            Collections.newSetFromMap(new WeakHashMap<LifecycleListener, Boolean>());


    //启动的标志
    private boolean isStarted;

    //销毁的标志
    private boolean isDestroyed;

    public void onStart() {
        isStarted = true;
        for (LifecycleListener lifecycleListener:lifecycleListeners){
            lifecycleListener.onStart();
        }
    }

    public void onStop() {
        isStarted = false;
        for (LifecycleListener lifecycleListener:lifecycleListeners){
            lifecycleListener.onStop();
        }
    }

    public void onDestroy() {
        isDestroyed = false;
        for (LifecycleListener lifecycleListener:lifecycleListeners){
            lifecycleListener.onDestroy();
        }
    }

    @Override
    public void addListener(@NonNull @NotNull LifecycleListener lifecycleListener) {
        lifecycleListeners.add(lifecycleListener);
        if (isDestroyed){
            lifecycleListener.onDestroy();
        }else if (isStarted){
            lifecycleListener.onStart();
        }else {
            //首次启动会默认 onStop 先停止，然后在onStart
            lifecycleListener.onStop();
        }
    }

    @Override
    public void removeListener(@NonNull @NotNull LifecycleListener lifecycleListener) {
        lifecycleListeners.remove(lifecycleListener);
    }
}
