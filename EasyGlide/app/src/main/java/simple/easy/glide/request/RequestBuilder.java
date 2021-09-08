package simple.easy.glide.request;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

import simple.easy.glide.engine.Engine;
import simple.easy.glide.work.ImageViewTarget;

/**
 * 对Request进行的封装
 */
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

    public RequestBuilder load(Uri uri){
        Engine.getInstance().load(uri,context);
        return this;
    }

    public RequestBuilder load(File file){
        Engine.getInstance().load(file,context);
        return this;
    }

    public RequestBuilder apply(RequestOptions requestOptions){
        this.requestOptions = requestOptions;
        return this;
    }

    public void into(ImageView imageView){
        final ImageViewTarget imageViewTarget = new ImageViewTarget(imageView);
        imageView.setImageResource(requestOptions.getResId());
        Engine.getInstance().into(requestOptions,imageViewTarget);
    }

}
