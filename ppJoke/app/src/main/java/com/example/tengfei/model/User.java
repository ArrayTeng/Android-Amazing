package com.example.tengfei.model;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.io.Serializable;

/**
 * @author tengfei
 * date 2020-02-07 16:35
 * email arrayadapter.cn@gmail.com
 * description
 */
public class User implements Serializable {


    /**
     * id : 1250
     * userId : 1578919786
     * name : 、蓅哖╰伊人为谁笑
     * avatar : http://qzapp.qlogo.cn/qzapp/101794421/FE41683AD4ECF91B7736CA9DB8104A5C/100
     * description : 这是一只神秘的jetpack
     * likeCount : 1
     * topCommentCount : 0
     * followCount : 0
     * followerCount : 14
     * qqOpenId : FE41683AD4ECF91B7736CA9DB8104A5C
     * expires_time : 1586695789903
     * score : 0
     * historyCount : 259
     * commentCount : 14
     * favoriteCount : 0
     * feedCount : 0
     * hasFollow : false
     */

    public int id;
    public int userId;
    public String name;
    public String avatar;
    public String description;
    public int likeCount;
    public int topCommentCount;
    public int followCount;
    public int followerCount;
    public String qqOpenId;
    public long expires_time;
    public int score;
    public int historyCount;
    public int commentCount;
    public int favoriteCount;
    public int feedCount;
    public boolean hasFollow;

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof User))
            return false;
        User newUser = (User) obj;
        return TextUtils.equals(name, newUser.name)
                && TextUtils.equals(avatar, newUser.avatar)
                && TextUtils.equals(description, newUser.description)
                && likeCount == newUser.likeCount
                && topCommentCount == newUser.topCommentCount
                && followCount == newUser.followCount
                && followerCount == newUser.followerCount
                && qqOpenId == newUser.qqOpenId
                && expires_time == newUser.expires_time
                && score == newUser.score
                && historyCount == newUser.historyCount
                && commentCount == newUser.commentCount
                && favoriteCount == newUser.favoriteCount
                && feedCount == newUser.feedCount
                && hasFollow == newUser.hasFollow;
    }
}
