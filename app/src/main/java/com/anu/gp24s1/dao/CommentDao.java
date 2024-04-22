package com.anu.gp24s1.dao;

import com.anu.gp24s1.pojo.vo.CommentVo;

import java.util.List;

public interface CommentDao {

    public List<CommentVo> viewComments(String postKey);

    public String addComment(String content, String postKey, String userKey);
}
