package com.anu.gp24s1.dao;

import com.anu.gp24s1.pojo.vo.CommentVo;

import java.util.List;

public interface CommentDao {

    /**
     * Given the key of a post, return all the view objects of comments belongs to this post.
     * @param postKey
     * @return List<CommentVo>
     */
    public List<CommentVo> viewComments(String postKey);

    /**
     * A user make a new comment below a post, return the key of this comment.
     * @param content
     * @param postKey
     * @param userKey
     * @return commentKey String
     */
    public String addComment(String content, String postKey, String userKey);
}
