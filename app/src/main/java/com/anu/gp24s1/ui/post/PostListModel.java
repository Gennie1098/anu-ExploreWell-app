package com.anu.gp24s1.ui.post;

import java.io.Serializable;

public class PostListModel implements Serializable{
    int userImg;
    String UserName;
    String time;
    String PostTitle;
    String locationTag;
    String activityTag;
    String postContent;
    int numberFollowing;
    int numberComments;

    public PostListModel(int userImg, String userName, String time, String postTitle,
                         String locationTag, String activityTag, String postContent,
                         int numberFollowing, int numberComments) {
        this.userImg = userImg;
        UserName = userName;
        this.time = time;
        PostTitle = postTitle;
        this.locationTag = locationTag;
        this.activityTag = activityTag;
        this.postContent = postContent;
        this.numberFollowing = numberFollowing;
        this.numberComments = numberComments;
    }

    public int getUserImg() {
        return userImg;
    }

    public String getUserName() {
        return UserName;
    }

    public String getTime() {
        return time;
    }

    public String getPostTitle() {
        return PostTitle;
    }

    public String getLocationTag() {
        return locationTag;
    }

    public String getActivityTag() {
        return activityTag;
    }

    public String getPostContent() {
        return postContent;
    }

    public int getNumberFollowing() {
        return numberFollowing;
    }

    public int getNumberComments() {
        return numberComments;
    }
}
