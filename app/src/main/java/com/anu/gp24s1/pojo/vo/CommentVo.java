package com.anu.gp24s1.pojo.vo;

import java.util.Date;

/**
 * View object of comment, define how to show a comment
 */
public class CommentVo {

    private String content;

    private Date commentTime;

    private String userAvatar;

    private String username;

    public CommentVo() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
