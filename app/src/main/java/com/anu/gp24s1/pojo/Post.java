package com.anu.gp24s1.pojo;

import com.anu.gp24s1.pojo.vo.PostVo;

import java.util.Date;
import java.util.List;

public class Post {

    private String postKey;

    private String title;

    private String content;

    private String tag;

    private String location;

    private Date publishTime;

    private String authorKey;

    private int followingNumber;

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

    public void setFollowingNumber(int followingNumber) {
        this.followingNumber = followingNumber;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
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

    /**
     * Binary Tree Height
     * @author  u7284324    Lachlan Stewart
     *
     * @return the height of the node, zero if leaf
     * */
    private int getHeight()
    {
        int left_height = leftNode == null ? 0 : leftNode.getHeight();
        int right_height = rightNode == null ? 0 : rightNode.getHeight();
        return left_height == 0 && right_height == 0 ? 0 : 1 + Math.max(left_height, right_height);
    }

    /**
     * Binary Tree Balance Factor
     * @author  u7284324    Lachlan Stewart
     *
     * @return the height of the left node minus the height of the right node
     * */
    private int getBalanceFactor()
    {
        int left_height = leftNode == null ? 0 : leftNode.getHeight();
        int right_height = rightNode == null ? 0 : rightNode.getHeight();
        return left_height - right_height;
    }

    public void leftRotation() throws NullPointerException {

    }

    public void rightRotation() {

    }

    public void insert(Post newPost) {

    }

    /**
     * Implements binary search
     * @author  u7284324    Lachlan Stewart
     *
     * @param   title       the title of a Post that is being searched for
     * @return              the Post with this title, if it exists, else null
     * */
    public Post search(String title) {

        if (title.equals(this.title)) {
            // found it
            return this;
        }

        if (title.compareTo(this.title) < 0) {
            // title < this.title
            return this.leftNode == null ? null : leftNode.search(title);
        } else {
            // title > this.title
            return this.leftNode == null ? null : leftNode.search(title);
        }
    }
}
