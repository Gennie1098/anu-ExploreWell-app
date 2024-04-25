package com.anu.gp24s1.state;

import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.pojo.vo.UserVo;

import java.util.List;

/**
 *  Represents a session for the current user
 *  Manages the state of the user session and provide operations for the user
 */
public class UserSession {

    //current user state of this session
    private UserState userState;

    //the key of current user
    private String userKey;

    /**
     * Initializes the user session with a default state of LogoutSession
     * @author Qinjue Wu
     */
    public UserSession() {
        UserState defaultState = new LogoutSession(this);
        changeState(defaultState);
    }

    /**
     * Changes the state of the user session
     * @param state
     * @author Qinjue Wu
     */
    public void changeState(UserState state){
        userState = state;
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
