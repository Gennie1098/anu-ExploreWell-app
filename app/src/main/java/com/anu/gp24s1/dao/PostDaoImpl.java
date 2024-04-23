package com.anu.gp24s1.dao;

import com.anu.gp24s1.pojo.Post;
import com.anu.gp24s1.pojo.vo.PostVo;

import java.util.HashMap;
import java.util.List;

public class PostDaoImpl implements PostDao{

    private PostDaoImpl instance;

    private Post rootPost;

    private HashMap<String,Post> posts;

    private HashMap<String,List<String>> postsGroupByTag;

    private HashMap<String,List<String>> postsGroupsByLocation;

    public PostDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void getGroupsByTag() {

    }

    @Override
    public void getGroupsByLocation() {

    }

    @Override
    public List<PostVo> getRecommendationByTag(String tag) {
        return null;
    }

    @Override
    public List<PostVo> getRecommendationByLocation(String location) {
        return null;
    }

    @Override
    public String createPost(String title, String content, String tag, String location, String userKey) {
        return null;
    }

    @Override
    public boolean followPost(String postKey, String userKey) {
        return false;
    }

    @Override
    public PostVo viewPost(String postKey, String userKey) {
        return null;
    }

    @Override
    public void addComment(String commentKey, String postKey) {

    }

    @Override
    public List<PostVo> getGroupsOfPosts(List<String> postKeyList) {
        return null;
    }

    @Override
    public List<PostVo> getFollowingPostsByLocation(String location, List<String> postKeyList) {
        return null;
    }

    @Override
    public List<PostVo> searchPosts(String searchWords) {
        return null;
    }
}
