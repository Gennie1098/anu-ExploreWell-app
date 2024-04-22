package com.anu.gp24s1.dao;

import com.anu.gp24s1.pojo.vo.UserVo;

import java.util.List;

public interface UserDao {

    public String getUserName(String userKey);

    public String getAvatar(String userKey);

    public String getPassion(String userKey);

    public String getLocation(String userKey);

    public UserVo getProfile(String userKey);

    public boolean addOwnPost(String userKey, String postKey);

    public boolean addFollowingPost(String userKey, String postKey);

    public List<String> getFollowingPosts(String userKey);
}
