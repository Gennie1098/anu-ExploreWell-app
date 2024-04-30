package com.anu.gp24s1.pojo;

import com.anu.gp24s1.dao.PostDao;
import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.pojo.vo.UserVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a user in the system.
 */
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

    /**
     * Retrieve the data related to the user and create an instance to return
     * @return the UserVo object containing the details of the user
     * @author  u7793565    Qihua Huang
     * */
    public UserVo toUserVo() {
        UserVo userVo = new UserVo();
        userVo.setUsername(username);
        userVo.setLocation(location);
        userVo.setPassion(passion);
        userVo.setAvatar(avatar);
        PostDao postDao = PostDaoImpl.getInstance();
        List<PostVo> postVoList = new ArrayList<>();
        // Retrieve the information of own posts base on the post key in the list of own posts
        for (String postKey : ownPosts) {
            PostVo postVo = postDao.viewPost(postKey, userKey);
            if (postVo != null) {
                postVoList.add(postVo);
            }
        }
        userVo.setOwnPosts(postVoList);
        return userVo;
    }
}
