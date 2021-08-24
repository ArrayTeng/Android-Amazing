package simple.easy.glide.binding;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import simple.easy.glide.binding.inter.Lifecycle;
import simple.easy.glide.binding.inter.LifecycleListener;

public class ApplicationLifecycle implements Lifecycle {
    @Override
    public void addListener(@NonNull @NotNull LifecycleListener lifecycleListener) {
        lifecycleListener.onStart();
    }

    @Override
    public void removeListener(@NonNull @NotNull LifecycleListener lifecycleListener) {

    }
}
