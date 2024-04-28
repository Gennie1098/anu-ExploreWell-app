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

    /**
     * Retrieve a list of post recommendations based on tag
     * This allows different implementations based on the userState state
     * @return a list of PostVo object, containing the details of posts
     * @author  u7793565    Qihua Huang
     * */
    public List<PostVo> getRecommendationByTag() {
        return userState.getRecommendationByTag();
    }

    /**
     * Retrieve a list of post recommendations based on location
     * This allows different implementations based on the userState state
     * @return a list of PostVo object, containing the details of posts
     * @author  u7793565    Qihua Huang
     * */
    public List<PostVo> getRecommendationByLocation() {
        return userState.getRecommendationByLocation();
    }

    /**
     * Retrieve the profile information of the current user, used for user profile display
     * This allows different implementations based on the userState state
     * @return the UserVo object containing the details of the current user, or null
     * @author  u7793565    Qihua Huang
     * */
    public UserVo getProfile() {
        return userState.getProfile();
    }

    /**
     * Create a new post with the given title, content, tag, and location
     * This allows different implementations based on the userState state
     * @param title
     * @param content
     * @param tag
     * @param location
     * @return whether the operation is successful or not
     * @author  u7793565    Qihua Huang
     * */
    public boolean createPost(String title, String content, String tag, String location){
        return userState.createPost(title, content, tag, location);
    }

    /**
     * Record the actions of users following posts by its key
     * This allows different implementations based on the userState state
     * @param postKey
     * @return whether the operation is successful or not
     * @author  u7793565    Qihua Huang
     * */
    public boolean followPost(String postKey){
        return userState.followPost(postKey);
    }

    /**
     * Retrieve the details of the post by the given post key
     * This allows different implementations based on the userState state
     * @param postKey
     * @return the PostVo object containing the details of the post, or null
     * @author  u7793565    Qihua Huang
     * */
    public PostVo viewPost(String postKey) {
        return userState.viewPost(postKey);
    }

    /**
     * Add a new comment with the given postkey and content
     * This allows different implementations based on the userState state
     * @param postKey
     * @param content
     * @return whether the operation is successful or not
     * @author  u7793565    Qihua Huang
     * */
    public boolean addComment(String postKey, String content){
        return userState.addComment(postKey, content);
    }

    /**
     * Retrieve grouped followed posts
     * This allows different implementations based on the userState state
     * @return a list of posts of grouped followed posts
     * @author  u7793565    Qihua Huang
     * */
    public List<String> viewFollowingGroups(){
        return userState.viewFollowingGroups();
    }

    /**
     * Retrieve followed posts by location search
     * This allows different implementations based on the userState state
     * @param location
     * @return a list of grouped posts of followed posts about specific location
     * @author  u7793565    Qihua Huang
     * */
    public List<PostVo> viewFollowingPosts(String location){
        return userState.viewFollowingPosts(location);
    }

    /**
     * Retrieve followed posts by keyword search
     * This allows different implementations based on the userState state
     * @param searchWords
     * @return a list of posts obtained by keyword search
     * @author  u7793565    Qihua Huang
     * */
    public List<PostVo> searchPosts(String searchWords){
        return userState.searchPosts(searchWords);
    }
}
