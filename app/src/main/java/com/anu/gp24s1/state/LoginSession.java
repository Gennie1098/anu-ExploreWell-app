package com.anu.gp24s1.state;

import com.anu.gp24s1.dao.PostDao;
import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.dao.UserDao;
import com.anu.gp24s1.dao.UserDaoImpl;
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
        UserDao userDao = UserDaoImpl.getInstance();
        String userKey = getUserSession().getUserKey();
        String passion = userDao.getPassion(userKey);
        PostDao postDao = PostDaoImpl.getInstance();
        return postDao.viewListOfPosts(postDao.getRecommendationByTag(passion),userKey);
    }

    @Override
    public List<PostVo> getRecommendationByLocation() {
        UserDao userDao = UserDaoImpl.getInstance();
        String userKey = getUserSession().getUserKey();
        String location = userDao.getLocation(userKey);
        PostDao postDao = PostDaoImpl.getInstance();
        return postDao.viewListOfPosts(postDao.getRecommendationByLocation(location),userKey);
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

    /**
     * Find all posts followed by the current user,
     * and group these posts by location, then return the group locations.
     * @return locations List<String>
     * @author Qinjue Wu
     */
    @Override
    public List<String> viewFollowingGroups() {
        String userKey = getUserSession().getUserKey();
        UserDao userDao = UserDaoImpl.getInstance();
        PostDao postDao = PostDaoImpl.getInstance();
        return postDao.getGroupsOfPosts(userDao.getFollowingPosts(userKey));
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
