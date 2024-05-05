package com.anu.gp24s1.ui.post;

public class CommentModel {
    int userImg;
    String UserName;
    String time;
    String content;

    public CommentModel(int userImg, String userName, String time, String content) {
        this.userImg = userImg;
        UserName = userName;
        this.time = time;
        this.content = content;
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

    public String getContent() {
        return content;
    }
}
