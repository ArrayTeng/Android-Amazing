package com.example.tengfei.model;

import androidx.annotation.Nullable;

/**
 * @author tengfei
 * date 2020-02-07 16:50
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Ugc {


    /**
     * likeCount : 108
     * shareCount : 10
     * commentCount : 10
     * hasFavorite : false
     * hasLiked : false
     * hasdiss : false
     * hasDissed : false
     */

    public int likeCount;
    public int shareCount;
    public int commentCount;
    public boolean hasFavorite;
    public boolean hasLiked;
    public boolean hasdiss;
    public boolean hasDissed;


    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof Ugc))
            return false;
        Ugc newUgc = (Ugc) obj;
        return likeCount == newUgc.likeCount
                && shareCount == newUgc.shareCount
                && commentCount == newUgc.commentCount
                && hasFavorite == newUgc.hasFavorite
                && hasLiked == newUgc.hasLiked
                && hasdiss == newUgc.hasdiss;
    }

}
