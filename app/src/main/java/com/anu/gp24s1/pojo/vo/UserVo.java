package com.anu.gp24s1.pojo.vo;

import java.util.List;

/**
 * View object of user, define how to show a user's profile
 */
public class UserVo {

    private String username;

    private String location;

    private String passion;

    private String avatar;

    private List<PostVo> ownPosts;

    public UserVo() {
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

    public List<PostVo> getOwnPosts() {
        return ownPosts;
    }

    public void setOwnPosts(List<PostVo> ownPosts) {
        this.ownPosts = ownPosts;
    }
}
