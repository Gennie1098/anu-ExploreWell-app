package com.anu.gp24s1.dao;

import com.anu.gp24s1.pojo.User;
import com.anu.gp24s1.pojo.vo.UserVo;

import java.util.HashMap;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private UserDaoImpl instance;

    private HashMap<String, User> users;

    public UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public String getUserName(String userKey) {
        return null;
    }

    @Override
    public String getAvatar(String userKey) {
        return null;
    }

    @Override
    public String getPassion(String userKey) {
        return null;
    }

    @Override
    public String getLocation(String userKey) {
        return null;
    }

    @Override
    public UserVo getProfile(String userKey) {
        return null;
    }

    @Override
    public boolean addOwnPost(String userKey, String postKey) {
        return false;
    }

    @Override
    public boolean addFollowingPost(String userKey, String postKey) {
        return false;
    }

    @Override
    public List<String> getFollowingPosts(String userKey) {
        return null;
    }
}
