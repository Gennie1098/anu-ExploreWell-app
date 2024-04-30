package com.anu.gp24s1.state;

import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.pojo.Post;
import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.pojo.vo.UserVo;

import java.util.List;

/**
 * Represents a session when the user is logged in.
 */
public class LoginSession extends UserState{
    public LoginSession(UserSession userSession) {
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

    /**
     * Adds a post. Inserts this post into the local tree and adds to the database
     *
     * @author  u7284324    Lachlan Stewart
     * */
    @Override
    public boolean createPost(String title, String content, String tag, String location) {

        // PostDaoImpl.getInstance().createPost(title, content, tag, location, );


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

    /**
     * Adds a comment to a post, updating the local Post and adding a Comment object and
     * adding this to the database
     *
     * @author  u7284234    Lachlan Stewart
     *
     * @param   postKey     The key associated with the post that the comment is being added to
     * @param   content     The message string of the comment
     * */
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
