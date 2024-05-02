package com.anu.gp24s1.ui.home;

public class RePostsByLocationModel {
    int userAva;
    String userName;
    String location;
    String activity;
    String postTitle;
    int numberOfFollowing;
    int numberOfComments;

    public RePostsByLocationModel(int userAva, String userName, String location, String activity,
                                  String postTitle, int numberOfFollowing, int numberOfComments) {
        this.userAva = userAva;
        this.userName = userName;
        this.location = location;
        this.activity = activity;
        this.postTitle = postTitle;
        this.numberOfFollowing = numberOfFollowing;
        this.numberOfComments = numberOfComments;
    }


    public int getUserAva() {
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
}
