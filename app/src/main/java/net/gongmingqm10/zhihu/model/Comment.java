package net.gongmingqm10.zhihu.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Comment {
    private int id;
    private String body;
    @SerializedName("likes_count")
    private int likesCount;
    @SerializedName("likes_url")
    private String likesUrl;
    @SerializedName("created_at")
    private String createdAt;
    private User user;
}
