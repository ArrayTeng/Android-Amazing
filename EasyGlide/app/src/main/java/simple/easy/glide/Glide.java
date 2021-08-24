package simple.easy.glide;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;

public class Glide {

    private static volatile Glide glide;

    private RequestManagerRetriever requestManagerRetriever;

    public Glide(){
        requestManagerRetriever = new RequestManagerRetriever();
    }

    public static RequestManager with(Activity activity){
        return getRetriever(activity).get(activity);
    }


    public static RequestManagerRetriever getRetriever(Context context){
        return Glide.get(context).getRequestManagerRetriever();
    }

    public static  Glide get(Context context) {
        if (glide == null){
            synchronized (Glide.class){
                if (glide == null){
                    glide = new Glide();
                }
            }
        }
        return glide;
    }

    private RequestManagerRetriever getRequestManagerRetriever(){
        return requestManagerRetriever;
    }


}
