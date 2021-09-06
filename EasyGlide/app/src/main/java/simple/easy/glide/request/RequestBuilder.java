package simple.easy.glide.request;

import android.content.Context;
import android.widget.ImageView;


import simple.easy.glide.engine.Engine;
import simple.easy.glide.work.ImageViewTarget;

/**
 * 对Request进行的封装
 * */
public class RequestBuilder {

    private String url;

    private Context context;

    private int resId;

    private RequestListener requestListener;

    private RequestOptions requestOptions;

    public RequestBuilder(Context context) {
        this.context = context;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestBuilder load(String url){
        Engine.getInstance().load(url,context);
        return this;
    }

    public RequestBuilder apply(RequestOptions requestOptions){
        this.requestOptions = requestOptions;
        return this;
    }

    public void into(ImageView imageView){
        ImageViewTarget imageViewTarget = new ImageViewTarget(imageView);
        Engine.getInstance().into(requestOptions,imageViewTarget);
    }

}
