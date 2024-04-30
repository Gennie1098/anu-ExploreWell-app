package com.anu.gp24s1.dao;

import com.anu.gp24s1.pojo.Post;
import com.anu.gp24s1.pojo.vo.PostVo;

import java.util.List;

public interface PostDao {

    public List<PostVo> getRecommendationByTag(String tag);

    public List<PostVo> getRecommendationByLocation(String location);

    public String createPost(String title, String content, String tag, String location, String userKey);

    public boolean followPost(String postKey, String userKey);

    public PostVo viewPost(String postKey, String userKey);

    public void addComment(String commentKey,String postKey);

    public List<PostVo> getGroupsOfPosts(List<String> postKeyList);

    public List<PostVo> getFollowingPostsByLocation(String location, List<String> postKeyList);

    public List<PostVo> searchPosts(String searchWords);
}
