package com.example.tengfei.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.libcommon.utils.PixUtils;
import com.example.tengfei.R;


/**
 * @author tengfei
 * date 2020-02-08 10:41
 * email arrayadapter.cn@gmail.com
 * description
 */
public class ListPlayView extends FrameLayout {

    private View bufferView;

    private PPImageView cover, blur, playBtn;
    private String category;
    private String videoUrl;


    public ListPlayView(@NonNull Context context) {
        this(context, null);
    }

    public ListPlayView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListPlayView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_player, this, true);

        bufferView = findViewById(R.id.buffer_view);
        cover = findViewById(R.id.cover);
        blur = findViewById(R.id.blur_background);
        playBtn = findViewById(R.id.play_btn);

    }

    /**
     * @param widthPx  视频宽度
     * @param heightPx 视频高度
     * @param coverUrl 封面URL
     * @param videoUrl 视频URL
     */
    public void bindData(String category, int widthPx, int heightPx, String coverUrl, String videoUrl) {
        this.category = category;
        this.videoUrl = videoUrl;

        cover.setImageUrl(cover, coverUrl, false);

        if (widthPx < heightPx) {
            blur.setBlurBackground(coverUrl, 10);
            blur.setVisibility(View.VISIBLE);
        } else {
            blur.setVisibility(View.INVISIBLE);
        }

        setSize(widthPx, heightPx);
    }

    private void setSize(int widthPx, int heightPx) {

        int maxWidth = PixUtils.getScreenWidth();
        int maxHeight = PixUtils.getScreenWidth();

        //控件的宽高
        int layoutWidth = maxWidth;
        int layoutHeight = 0;

        //封面的宽高
        int coverWidth;
        int coverHeight;

        if (widthPx > heightPx) {
            coverWidth = maxWidth;
            layoutHeight = coverHeight = (int) (heightPx / (widthPx * 1.0f / maxWidth));
        } else {
            layoutHeight = coverHeight = maxHeight;
            coverWidth = (int) (widthPx / (heightPx * 1.0F / maxHeight));
        }

        //设置组件的宽高
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = layoutWidth;
        params.height = layoutHeight;
        setLayoutParams(params);

        //设置高斯模糊组件的宽高
        ViewGroup.LayoutParams blurParams = blur.getLayoutParams();
        blurParams.width = layoutWidth;
        blurParams.height = layoutHeight;
        blur.setLayoutParams(blurParams);

        //设置封面图的宽高
        FrameLayout.LayoutParams coverParams = (LayoutParams) cover.getLayoutParams();
        coverParams.width = coverWidth;
        coverParams.height = coverHeight;
        coverParams.gravity = Gravity.CENTER;
        cover.setLayoutParams(coverParams);

        FrameLayout.LayoutParams playBtnParams = (LayoutParams) playBtn.getLayoutParams();
        playBtnParams.gravity = Gravity.CENTER;
        playBtn.setLayoutParams(playBtnParams);

    }

}
