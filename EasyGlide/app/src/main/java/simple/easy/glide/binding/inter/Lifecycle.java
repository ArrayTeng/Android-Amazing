package simple.easy.glide.binding.inter;

import androidx.annotation.NonNull;


public interface Lifecycle {

    void addListener(@NonNull LifecycleListener lifecycleListener);

    void removeListener(@NonNull LifecycleListener lifecycleListener);

}
