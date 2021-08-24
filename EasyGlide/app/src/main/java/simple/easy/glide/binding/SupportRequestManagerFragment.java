package simple.easy.glide.binding;

import androidx.fragment.app.Fragment;

import simple.easy.glide.RequestManager;

public class SupportRequestManagerFragment extends Fragment {

    private final ActivityFragmentLifecycle lifecycle;

    private RequestManager requestManager;

    public SupportRequestManagerFragment(){
        this(new ActivityFragmentLifecycle());
    }

    public SupportRequestManagerFragment(ActivityFragmentLifecycle lifecycle){
        this.lifecycle = lifecycle;
    }

    public void setRequestManager(RequestManager requestManager){
        this.requestManager = requestManager;
    }

    public RequestManager getRequestManager(){
        return requestManager;
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

    public ActivityFragmentLifecycle getGlideLifecycle(){
        return lifecycle;
    }
}
