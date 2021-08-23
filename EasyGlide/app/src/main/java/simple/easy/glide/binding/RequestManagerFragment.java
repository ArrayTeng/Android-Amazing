package simple.easy.glide.binding;

import android.annotation.SuppressLint;
import android.app.Fragment;

@SuppressLint("ValidFragment")
public class RequestManagerFragment extends Fragment {

    private ActivityFragmentLifecycle lifecycle;

    @SuppressLint("ValidFragment")
    public RequestManagerFragment(ActivityFragmentLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycle.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycle.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycle.onDestroy();
    }
}
