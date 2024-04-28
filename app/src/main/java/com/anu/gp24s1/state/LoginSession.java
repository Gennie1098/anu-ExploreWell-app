package com.anu.gp24s1.state;

import com.anu.gp24s1.dao.PostDao;
import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.dao.UserDao;
import com.anu.gp24s1.dao.UserDaoImpl;
import com.anu.gp24s1.pojo.Post;
import com.anu.gp24s1.pojo.User;
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

    /**
     * Retrieve the information of user according to the user key
     * @return the UserVo object containing the details of the user, or null
     * @author  u7793565    Qihua Huang
     * */
    @Override
    public UserVo getProfile() {
        UserDao userDao = UserDaoImpl.getInstance();
        // TODO: don't know how to get current user key
        User user = new User();
        String userKey = user.getUserKey();
        return userDao.getProfile(userKey);
    }

    @Override
    public boolean createPost(String title, String content, String tag, String location) {
        return false;
    }

    /**
     * Record the actions of users following posts by its key.
     * @param postKey
     * @return whether the operation is successful or not
     * @author  u7793565    Qihua Huang
     * */
    @Override
    public boolean followPost(String postKey) {
        PostDao postDao = PostDaoImpl.getInstance();
        UserDao userDao = UserDaoImpl.getInstance();
        // TODO: don't know how to get current user key
        User user = new User();
        String userKey = user.getUserKey();
        boolean result1 = postDao.followPost(postKey, userKey);
        boolean result2 = userDao.addFollowingPost(userKey, postKey);
        return result1 && result2;
    }

    /**
     * Retrieve the details of the post by the given post key
     * @param postKey
     * @return the PostVo object containing the details of the post, or null
     * @author  u7793565    Qihua Huang
     * */
    @Override
    public PostVo viewPost(String postKey) {
        PostDao postDao = PostDaoImpl.getInstance();
        UserDao userDao = UserDaoImpl.getInstance();
        // TODO: don't know how to get current user key
        User user = new User();
        String userKey = user.getUserKey();
        return postDao.viewPost(postKey, userKey);
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
