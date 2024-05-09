package com.anu.gp24s1.state;

import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.pojo.vo.UserVo;

import java.util.List;

/**
 * Represents the state of a user session, and define the common behavior of all user states.
 */
public abstract class UserState {

    //the context associate with this state
    protected UserSession userSession;

    /**
     * Construct a UserState object, and initialize the user session associated with this state.
     * @param userSession UserSession
     * @author Qinjue Wu
     */
    public UserState(UserSession userSession) {
        this.userSession = userSession;
    }

    public abstract boolean login(String username, String password);

    public abstract boolean logout();

    public abstract List<PostVo> getRecommendationByTag();

    public abstract List<PostVo> getRecommendationByLocation();

    public abstract UserVo getProfile();

    public abstract String createPost(String title, String content, String tag, String location);

    public abstract boolean followPost(String postKey);

    public abstract PostVo viewPost(String postKey);

    public abstract boolean addComment(String postKey, String content);

    public abstract List<String> viewFollowingGroups();

    public abstract List<PostVo> viewFollowingPosts(String group);

    public abstract List<PostVo> searchPosts(String searchWords);

}
