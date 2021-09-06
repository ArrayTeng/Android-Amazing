package simple.easy.glide.work;

import android.graphics.Bitmap;
import android.widget.ImageView;

import simple.easy.glide.binding.inter.LifecycleListener;

public class ImageViewTarget implements LifecycleListener {

    public ImageViewTarget(ImageView imageView) {
        this.imageView = imageView;
    }

    ImageView imageView;

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    public void setResource(Bitmap resource){
        imageView.setImageBitmap(resource);
    }
}
