package simple.easy.glide.binding;

import android.annotation.SuppressLint;
import android.app.Fragment;

import simple.easy.glide.RequestManager;

@SuppressLint("ValidFragment")
public class RequestManagerFragment extends Fragment {

    private ActivityFragmentLifecycle lifecycle;

    private RequestManager requestManager;

    public RequestManagerFragment(){
        this(new ActivityFragmentLifecycle());
    }

    @SuppressLint("ValidFragment")
    public RequestManagerFragment(ActivityFragmentLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public void setRequestManager(RequestManager requestManager){
        this.requestManager = requestManager;
    }

    public RequestManager getRequestManager(){
        return requestManager;
    }

    public ActivityFragmentLifecycle getGlideLifecycle(){
        return lifecycle;
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
