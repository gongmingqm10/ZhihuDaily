package net.gongmingqm10.zhihu.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String username;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("avatar_url")
    private String avatarUrl;
    private String bio;
    private String location;
    private Link links;
    @SerializedName("comments_received_count")
    private int commentsCount;
    @SerializedName("followers_count")
    private int followersCount;
    @SerializedName("followings_count")
    private int followingsCount;
    @SerializedName("likes_count")
    private int likesCount;
    @SerializedName("likes_received_count")
    private int likesReceivedCount;
    @SerializedName("projects_count")
    private int projectsCount;
    @SerializedName("shots_count")
    private int shoutsCount;
    @SerializedName("teams_count")
    private int teamsCount;
    @SerializedName("buckets_url")
    private String bucketsUrl;
    @SerializedName("followers_url")
    private String followersUrl;
    @SerializedName("likes_url")
    private String likesUrl;
    @SerializedName("projects_url")
    private String projectsUrl;
    @SerializedName("shots_url")
    private String shotsUrl;

}
