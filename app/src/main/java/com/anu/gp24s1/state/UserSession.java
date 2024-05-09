package com.anu.gp24s1.state;

import com.anu.gp24s1.dao.UserDao;
import com.anu.gp24s1.dao.UserDaoImpl;
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

    private static UserSession instance;

    //the key of current user
    private String userKey;

    /**
     * Initializes the user session with a default state of LogoutSession
     * @author Qinjue Wu
     */
    private UserSession() {
        UserState defaultState = new LogoutSession(this);
        changeState(defaultState);
    }

    public static UserSession getInstance()
    {
        if(instance == null)
        {
            instance = new UserSession();
        }
        return instance;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    /**
     * Changes the state of the user session
     * @param state UserState
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
     * Recommend posts to the current user, according to his passion tag.
     * @return List<PostVo>
     * @author Qinjue Wu
     */
    public List<PostVo> getRecommendationByTag() {
        return userState.getRecommendationByTag();
    }

    /**
     * Recommend posts to the current user, according to his location.
     * @return List<PostVo>
     */
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
     * Asks the user state to create a post
     * @author  u7284324    Lachlan Stewart
     * @return  true if successful
     * @see     UserState#createPost
     * */
    public String createPost(String title, String content, String tag, String location)  {
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
     * Asks the user state to add a comment
     * @author  u7284324    Lachlan Stewart
     * @see UserState#addComment
     * @return  true if successful
     * */
    public boolean addComment(String postKey, String content)
    {
        return userState.addComment(postKey, content);
    }

    /**
     * Retrieve grouped followed posts
     * This allows different implementations based on the userState state
     * @return a list of posts of grouped followed posts
     * */
    public List<String> viewFollowingGroups()
    {
        return userState.viewFollowingGroups();
    }

    public List<PostVo> viewFollowingPosts(String group){
        return userState.viewFollowingPosts(group);
    }

    public List<PostVo> searchPosts(String searchWords){
        return userState.searchPosts(searchWords);
    }
}
