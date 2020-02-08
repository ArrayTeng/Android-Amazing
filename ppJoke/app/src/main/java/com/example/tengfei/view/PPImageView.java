package com.example.tengfei.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.libcommon.utils.PixUtilsKt;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * @author tengfei
 * date 2020-02-07 17:06
 * email arrayadapter.cn@gmail.com
 * description
 */
public class PPImageView extends AppCompatImageView {
    public PPImageView(Context context) {
        this(context, null);
    }

    public PPImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PPImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //使用 databinding 可以使用 BindingAdapter 来标记一个方法供布局文件使用
    @SuppressLint("CheckResult")
    @BindingAdapter(value = {"imageUrl", "isCircle"}, requireAll = false)
    public static void setImageUrl(PPImageView ppImageView, String imageUrl, boolean isCircle) {
        RequestBuilder<Drawable> requestBuilder = Glide.with(ppImageView).load(imageUrl);
        if (isCircle) {
            requestBuilder.transform(new CircleCrop());
        }
        ViewGroup.LayoutParams layoutParams = ppImageView.getLayoutParams();
        if (layoutParams != null && layoutParams.width > 0 && layoutParams.height > 0) {
            requestBuilder.override(layoutParams.width, layoutParams.height);
        }
        requestBuilder.into(ppImageView);

    }


    public void bindData(int widthPx, int heightPx, int marginLeft, String imageUrl) {
        bindData(widthPx, heightPx, marginLeft, PixUtilsKt.getScreenWidth(), PixUtilsKt.getScreenWidth(), imageUrl);
    }

    public void bindData(int widthPx, int heightPx, int marginLeft, int maxWidth, int maxHeight, String imageUrl) {
        if (widthPx <= 0 || heightPx <= 0) {
            Glide.with(this).load(imageUrl).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    int width = resource.getIntrinsicWidth();
                    int height = resource.getIntrinsicHeight();

                    setSize(width, height, marginLeft, maxWidth, maxHeight);

                    setImageDrawable(resource);
                }
            });
            return;
        }

        setSize(widthPx, heightPx, marginLeft, maxWidth, maxHeight);
        setImageUrl(this, imageUrl, false);

    }

    private void setSize(int widthPx, int heightPx, int marginLeft, int maxWidth, int maxHeight) {
        int finalWidth, finalHeight;
        if (widthPx > heightPx) {
            finalWidth = maxWidth;
            finalHeight = (int) (heightPx / (widthPx * 1.0f / maxWidth));
        } else {
            finalHeight = maxHeight;
            finalWidth = (int) (widthPx / (heightPx * 1.0f / maxHeight));
        }

        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(finalWidth, finalHeight);
        marginLayoutParams.leftMargin = heightPx > PixUtilsKt.dp2px(widthPx) ? marginLeft : 0;
        setLayoutParams(marginLayoutParams);
    }

    public void setBlurBackground(String coverUrl, int radious) {
        //设置高斯模糊
        Glide.with(this).load(coverUrl)
                .override(50)
                .transform(new BlurTransformation())
                .dontAnimate()
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        setBackground(resource);
                    }
                });
    }


}
