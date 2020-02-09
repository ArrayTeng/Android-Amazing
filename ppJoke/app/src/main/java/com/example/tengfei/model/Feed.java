package com.example.tengfei.model;

import android.text.TextUtils;

import androidx.annotation.Nullable;

/**
 * @author tengfei
 * date 2020-02-07 16:52
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Feed {

    public static final int TYPE_IMAGE_TEXT = 1;//图文
    public static final int TYPE_VIDEO = 2;//视频


    /**
     * id : 428
     * itemId : 1578976510452
     * itemType : 2
     * createTime : 1578977844500
     * duration : 8
     * feeds_text : 2020他来了，就在眼前了
     * authorId : 1578919786
     * activityIcon : null
     * activityText : 2020新年快乐
     * width : 960
     * height : 540
     * url : https://pipijoke.oss-cn-hangzhou.aliyuncs.com/New%20Year%20-%2029212-video.mp4
     * cover : https://pipijoke.oss-cn-hangzhou.aliyuncs.com/2020%E5%B0%81%E9%9D%A2%E5%9B%BE.png
     */

    public int id;
    public long itemId;
    public int itemType;
    public long createTime;
    public int duration;
    public String feeds_text;
    public int authorId;
    public String activityIcon;
    public String activityText;
    public int width;
    public int height;
    public String url;
    public String cover;

    public User author;
    public Comment topComment;
    public Ugc ugc;


    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof Feed))
            return false;
        Feed newFeed = (Feed) obj;
        return id == newFeed.id
                && itemId == newFeed.itemId
                && itemType == newFeed.itemType
                && createTime == newFeed.createTime
                && duration == newFeed.duration
                && TextUtils.equals(feeds_text, newFeed.feeds_text)
                && authorId == newFeed.authorId
                && TextUtils.equals(activityIcon, newFeed.activityIcon)
                && TextUtils.equals(activityText, newFeed.activityText)
                && width == newFeed.width
                && height == newFeed.height
                && TextUtils.equals(url, newFeed.url)
                && TextUtils.equals(cover, newFeed.cover)
                && (author != null && author.equals(newFeed.author))
                && (topComment != null && topComment.equals(newFeed.topComment))
                && (ugc != null && ugc.equals(newFeed.ugc));
    }
}
