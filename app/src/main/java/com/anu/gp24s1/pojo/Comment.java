package com.anu.gp24s1.pojo;

import com.anu.gp24s1.dao.UserDao;
import com.anu.gp24s1.dao.UserDaoImpl;
import com.anu.gp24s1.pojo.vo.CommentVo;

import java.util.Date;

/**
 * Represents a comment made on a post in the system.
 */
public class Comment {
    private String commentKey;

    private String content;

    private Date commentTime;

    private String authorKey;

    private String postKey;

    public Comment() {
    }

    public String getCommentKey() {
        return commentKey;
    }

    public void setCommentKey(String commentKey) {
        this.commentKey = commentKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getAuthorKey() {
        return authorKey;
    }

    public void setAuthorKey(String authorKey) {
        this.authorKey = authorKey;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public CommentVo toCommentVo(){
        CommentVo commentVo = new CommentVo();
        UserDao userDao = UserDaoImpl.getInstance();
        commentVo.setUserAvatar(userDao.getAvatar(authorKey));
        commentVo.setUsername(userDao.getUsername(authorKey));
        commentVo.setCommentTime(commentTime);
        commentVo.setContent(content);
        return commentVo;
    }
}
