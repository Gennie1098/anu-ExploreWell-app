package com.anu.gp24s1.dao;

import com.anu.gp24s1.pojo.Comment;
import com.anu.gp24s1.pojo.vo.CommentVo;

import java.util.List;
import java.util.Map;

public class CommentDaoImpl implements CommentDao{

    private CommentDaoImpl instance;

    private Map<String, Comment> comments;

    public CommentDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<CommentVo> viewComments(String postKey) {
        return null;
    }

    @Override
    public String addComment(String content, String postKey, String userKey) {
        return null;
    }
}
