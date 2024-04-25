package com.anu.gp24s1.state;

import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.pojo.vo.UserVo;

import java.util.List;

/**
 * Represents a session when the user is logged out.
 */
public class LogoutSession extends UserState{
    public LogoutSession(UserSession userSession) {
        super(userSession);
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public List<PostVo> getRecommendationByTag() {
        return null;
    }

    @Override
    public List<PostVo> getRecommendationByLocation() {
        return null;
    }

    @Override
    public UserVo getProfile() {
        return null;
    }

    @Override
    public boolean createPost(String title, String content, String tag, String location) {
        return false;
    }

    @Override
    public boolean followPost(String postKey) {
        return false;
    }

    @Override
    public PostVo viewPost(String postKey) {
        return null;
    }

    @Override
    public boolean addComment(String postKey, String content) {
        return false;
    }

    @Override
    public List<String> viewFollowingGroups() {
        return null;
    }

    @Override
    public List<PostVo> viewFollowingPosts(String location) {
        return null;
    }

    @Override
    public List<PostVo> searchPosts(String searchWords) {
        return null;
    }
}
