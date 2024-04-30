package com.anu.gp24s1.state;

import com.anu.gp24s1.dao.CommentDao;
import com.anu.gp24s1.dao.CommentDaoImpl;
import com.anu.gp24s1.dao.PostDao;
import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.dao.UserDao;
import com.anu.gp24s1.dao.UserDaoImpl;
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

    private final UserDao userDao = UserDaoImpl.getInstance();

    private final PostDao postDao = PostDaoImpl.getInstance();

    private final CommentDao commentDao = CommentDaoImpl.getInstance();

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
        String userKey = userSession.getUserKey();
        String passion = userDao.getPassion(userKey);
        return postDao.viewListOfPosts(postDao.getRecommendationByTag(passion),userKey);
    }

    @Override
    public List<PostVo> getRecommendationByLocation() {
        String userKey = userSession.getUserKey();
        String location = userDao.getLocation(userKey);
        return postDao.viewListOfPosts(postDao.getRecommendationByLocation(location),userKey);
    }

    @Override
    public UserVo getProfile() {
        return null;
    }

    @Override
    public boolean createPost(String title, String content, String tag, String location) {
        String postKey = "";
        try {
            postKey = PostDaoImpl.getInstance().createPost(title, content, tag, location, super.userSession.getUserKey());
        } catch (Exception e) {
            return false;
        }

        // TODO: What happens to postKey?

        return true;
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

        // Add comment via Comment dao
        String commentKey = CommentDaoImpl.getInstance().addComment(content, postKey, super.userSession.getUserKey());

        // Add comment to post:
        PostDaoImpl.getInstance().addComment(commentKey, content);

        return true;
    }

    /**
     * Find all posts followed by the current user,
     * and group these posts by location and tag, then return the groups.
     * @return locations List<String>
     * @author Qinjue Wu
     */
    @Override
    public List<String> viewFollowingGroups() {
        String userKey = userSession.getUserKey();
        return postDao.getGroupsOfPosts(userDao.getFollowingPosts(userKey));
    }

    /**
     * Given the name of group
     * and return a list of posts followed by the user and belong to the group.
     * @param group String
     * @return List<PostVo>
     * @author Qinjue Wu
     */
    @Override
    public List<PostVo> viewFollowingPosts(String group) {
        String userKey = userSession.getUserKey();
        List<Post> followingPostsByGroup = postDao.getFollowingPostsByGroup(group, userDao.getFollowingPosts(userKey));
        return postDao.viewListOfPosts(followingPostsByGroup,userKey);
    }

    @Override
    public List<PostVo> searchPosts(String searchWords) {
        return null;
    }
}
