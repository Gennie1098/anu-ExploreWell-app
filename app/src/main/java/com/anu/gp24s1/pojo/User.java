package com.anu.gp24s1.pojo;

import com.anu.gp24s1.pojo.vo.UserVo;

import java.util.List;

public class User {

    private String userKey;

    private String username;

    private String location;

    private String passion;

    private String avatar;

    private List<String> ownPosts;

    private List<String> followingPosts;

    public User() {
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassion() {
        return passion;
    }

    public void setPassion(String passion) {
        this.passion = passion;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getOwnPosts() {
        return ownPosts;
    }

    public void setOwnPosts(List<String> ownPosts) {
        this.ownPosts = ownPosts;
    }

    public List<String> getFollowingPosts() {
        return followingPosts;
    }

    public void setFollowingPosts(List<String> followingPosts) {
        this.followingPosts = followingPosts;
    }

    public UserVo toUserVo() {
        return null;
    }
}
