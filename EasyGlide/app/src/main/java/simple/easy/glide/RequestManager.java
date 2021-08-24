package simple.easy.glide;

import android.content.Context;

import simple.easy.glide.binding.inter.Lifecycle;

public class RequestManager {

    private static volatile RequestManager requestManager;

    private RequestManager(Lifecycle lifecycle, Context context){

    }

    public static RequestManager getInstance(Lifecycle lifecycle, Context context){
        if (requestManager == null){
            synchronized (RequestManager.class){
                if (requestManager == null){
                    requestManager = new RequestManager(lifecycle,context);
                }
            }
        }
        return requestManager;
    }
}
