package com.example.tengfei.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

/**
 * @author tengfei
 * date 2020-02-07 17:06
 * email arrayadapter.cn@gmail.com
 * description
 */
public class PPImageView extends AppCompatImageView {
    public PPImageView(Context context) {
        this(context,null);
    }

    public PPImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PPImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //使用 databinding 可以使用 BindingAdapter 来标记一个方法供布局文件使用
    @SuppressLint("CheckResult")
    @BindingAdapter(value = {"imageUrl","isCircle"},requireAll = false)
    public static void setImageUrl(PPImageView ppImageView, String imageUrl,boolean isCircle){
        RequestBuilder<Drawable> requestBuilder = Glide.with(ppImageView).load(imageUrl);
        if (isCircle){
            requestBuilder.transform(new CircleCrop());
        }
        ViewGroup.LayoutParams layoutParams = ppImageView.getLayoutParams();
        if (layoutParams!=null&&layoutParams.width>0&&layoutParams.height>0){
            requestBuilder.override(layoutParams.width,layoutParams.height);
        }
        requestBuilder.into(ppImageView);

    }
}
