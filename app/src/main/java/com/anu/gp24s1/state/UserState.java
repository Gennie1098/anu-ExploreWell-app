package com.anu.gp24s1.state;

import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.pojo.vo.UserVo;

import java.util.List;

public abstract class UserState {

    private UserSession userSession;

    public UserState(UserSession userSession) {
        this.userSession = userSession;
    }

    public abstract boolean login(String username, String password);

    public abstract boolean logout();

    public abstract List<PostVo> getRecommendationByTag();

    public abstract List<PostVo> getRecommendationByLocation();

    public abstract UserVo getProfile();

    public abstract boolean createPost(String title, String content, String tag, String location);

    public abstract boolean followPost(String postKey);

    public abstract PostVo viewPost(String postKey);

    public abstract boolean addComment(String postKey, String content);

    public abstract List<String> viewFollowingGroups();

    public abstract List<PostVo> viewFollowingPosts(String location);

    public abstract List<PostVo> searchPosts(String searchWords);

}
