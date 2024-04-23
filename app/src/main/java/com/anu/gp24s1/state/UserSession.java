package com.anu.gp24s1.state;

import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.pojo.vo.UserVo;

import java.util.List;

public class UserSession {

    private UserState userState;

    private String userKey;

    public UserSession() {
    }

    public void changeState(){

    }

    public boolean login(String username, String password) {
        return true;
    }

    public boolean logout() {
        return true;
    }

    public List<PostVo> getRecommendationByTag() {
        return null;
    }

    public List<PostVo> getRecommendationByLocation() {
        return null;
    }

    public UserVo getProfile() {
        return null;
    }

    public boolean createPost(String title, String content, String tag, String location){
        return true;
    }

    public boolean followPost(String postKey){
        return true;
    }

    public PostVo viewPost(String postKey) {
        return null;
    }

    public boolean addComment(String postKey, String content)
    {
        return true;
    }

    public List<String> viewFollowingGroups()
    {
        return null;
    }

    public List<PostVo> viewFollowingPosts(String location){
        return null;
    }

    public List<PostVo> searchPosts(String searchWords){
        return null;
    }
}
