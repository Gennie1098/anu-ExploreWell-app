package com.anu.gp24s1.ui.home;

public class RePostsByLocationModel {
    String postKey;
    String userAva;
    String userName;
    String location;
    String activity;
    String postTitle;
    int numberOfFollowing;
    int numberOfComments;

    public RePostsByLocationModel(String postKey,String userAva, String userName, String location, String activity,
                                  String postTitle, int numberOfFollowing, int numberOfComments) {
        this.postKey = postKey;
        this.userAva = userAva;
        this.userName = userName;
        this.location = location;
        this.activity = activity;
        this.postTitle = postTitle;
        this.numberOfFollowing = numberOfFollowing;
        this.numberOfComments = numberOfComments;
    }

    public String getPostKey() {
        return postKey;
    }

    public String getUserAva() {
        return userAva;
    }

    public String getUserName() {
        return userName;
    }

    public String getLocation() {
        return location;
    }

    public String getActivity() {
        return activity;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public int getNumberOfFollowing() {
        return numberOfFollowing;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfFollowing(int numberOfFollowing) {
        this.numberOfFollowing = numberOfFollowing;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }
}
