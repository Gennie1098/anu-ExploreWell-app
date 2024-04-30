package com.anu.gp24s1.dao;

import com.anu.gp24s1.pojo.Post;
import com.anu.gp24s1.pojo.vo.PostVo;

import java.util.List;

public interface PostDao {

    /**
     * Given the passion tag of the user, and recommend corresponding posts to users by the number of followers.
     * @param tag String
     * @return List<Post>
     * @author Qinjue Wu
     */
    public List<Post> getRecommendationByTag(String tag);

    /**
     * Given the location of the user, and recommend corresponding posts to users by the number of followers.
     * @param location String
     * @return List<Post>
     * @author Qinjue Wu
     */
    public List<Post> getRecommendationByLocation(String location);

    public String createPost(String title, String content, String tag, String location, String userKey);

    public boolean followPost(String postKey, String userKey);

    public PostVo viewPost(String postKey, String userKey);

    public void addComment(String commentKey,String postKey);

    public List<String> getGroupsOfPosts(List<String> postKeyList);

    public List<Post> getFollowingPostsByGroup(String group, List<String> postKeyList);

    public List<Post> searchPosts(String searchWords);

    /**
     * Convert a list of Post objects to PostVo objects.
     * @param posts List<Post>
     * @param userKey String
     * @return List<PostVo>
     * @author Qinjue Wu
     */
    public List<PostVo> viewListOfPosts(List<Post> posts,String userKey);
}
