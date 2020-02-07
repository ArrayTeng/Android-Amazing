package com.example.tengfei.model;

/**
 * @author tengfei
 * date 2020-02-07 16:48
 * email arrayadapter.cn@gmail.com
 * description
 */
public class Comment {


    /**
     * id : 1126
     * itemId : 1578976510452
     * commentId : 1579007787804000
     * userId : 1578919786
     * commentType : 1
     * createTime : 1579007787804
     * commentCount : 0
     * likeCount : 1001
     * commentText : 2020他来了，就在眼前了~Happy New Year
     * imageUrl :
     * videoUrl :
     * width : 0
     * height : 0
     * hasLiked : false
     */

    public int id;
    public long itemId;
    public long commentId;
    public int userId;
    public int commentType;
    public long createTime;
    public int commentCount;
    public int likeCount;
    public String commentText;
    public String imageUrl;
    public String videoUrl;
    public int width;
    public int height;
    public boolean hasLiked;
    public User author;
    public  Ugc ugc;
}
