package com.anu.gp24s1.pojo;

import com.anu.gp24s1.pojo.vo.PostVo;

import java.util.Date;
import java.util.List;

/**
 * Represents a post made by a user in the system.
 */
public class Post {

    private String postKey;

    private String title;

    private String content;

    private String tag;

    private String location;

    private Date publishTime;

    private String authorKey;

    private int followingNumber;

    private int commentsNumber;

    private List<String> followers;

    private List<String> comments;

    private Post leftNode;

    private Post rightNode;

    public Post() {
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getAuthorKey() {
        return authorKey;
    }

    public void setAuthorKey(String authorKey) {
        this.authorKey = authorKey;
    }

    public int getFollowingNumber() {
        return followingNumber;
    }

    public void setFollowingNumber(int followingNumer) {
        this.followingNumber = followingNumer;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public int getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(int commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public Post getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Post leftNode) {
        this.leftNode = leftNode;
    }

    public Post getRightNode() {
        return rightNode;
    }

    public void setRightNode(Post rightNode) {
        this.rightNode = rightNode;
    }

    public PostVo toPostVo()
    {
        return null;
    }

    public int getHeight()
    {
        return 0;
    }

    public int getBalanceFactor()
    {
        return 0;
    }

    public void leftRotation() {

    }

    public void rightRotation() {

    }

    public void insert(Post newPost) {

    }

    public Post search(String title) {
        return null;
    }
}
