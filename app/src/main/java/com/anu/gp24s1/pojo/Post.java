package com.anu.gp24s1.pojo;

import com.anu.gp24s1.dao.CommentDao;
import com.anu.gp24s1.dao.CommentDaoImpl;
import com.anu.gp24s1.dao.UserDao;
import com.anu.gp24s1.dao.UserDaoImpl;
import com.anu.gp24s1.pojo.vo.PostVo;

import java.util.ArrayList;
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

    private int followerNumber;

    private int commentsNumber;

    private List<String> followers;

    private List<String> comments;

    private Post leftNode;

    private Post rightNode;

    public Post() {

        // so that these are not null
        this.comments = new ArrayList<String>();
        this.followers = new ArrayList<String>();
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

    public int getFollowerNumber() {
        return followerNumber;
    }

    public void setFollowerNumber(int followerNumber) {
        this.followerNumber = followerNumber;
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

    /**
     * Get the data related to the post and create an instance to return
     * @author  u7793565    Qihua Huang
     *
     * @return PostVo
     * */
    public PostVo toPostVo(String userKey)
    {
        PostVo postVo = new PostVo();
        postVo.setPostKey(postKey);
        postVo.setTitle(title);
        postVo.setContent(content);
        postVo.setTag(tag);
        postVo.setLocation(location);
        postVo.setPublishTime(publishTime);
        postVo.setFollowerNumber(followerNumber);
        postVo.setCommentsNumber(commentsNumber);

        if (followers.contains(userKey)){
            postVo.setFollowing(true);
        }

        // Through the design pattern of singleton, getInstance will not read repeatedly.
        // TODO Qinjue please confirm this call
        UserDao userDao = UserDaoImpl.getInstance();
        postVo.setAuthorName(userDao.getUsername(authorKey));
        postVo.setAuthorAvatar(userDao.getAvatar(authorKey));
        CommentDao commentDao = CommentDaoImpl.getInstance();
        postVo.setComments(commentDao.viewComments(postKey));

        return postVo;
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

    /**
     * Left AVL tree rotation
     * @author  u7284324    Lachlan Stewart
     *
     * @throws  NullPointerException    If there is no right node
     * */
    private Post leftRotation() throws NullPointerException {

        if (rightNode == null) { throw new NullPointerException("Left rotation without a right node"); }

        Post rightOrig = rightNode;
        Post rightleft = rightOrig.leftNode;
        rightNode.setLeftNode(this);
        setRightNode(rightleft);

        return rightOrig;
    }

    /**
     * Right AVL tree rotation
     * @author  u7284324    Lachlan Stewart
     *
     * @throws  NullPointerException    If there is no left node
     * */
    private Post rightRotation() {
        if (leftNode == null) { throw new NullPointerException("Right rotation without a left node"); }

        Post leftOrig = leftNode;
        Post leftright = leftOrig.rightNode;
        leftNode.setRightNode(this);
        setLeftNode(leftright);

        return leftOrig;
    }

    /**
     * AVL Insertion with balancing
     *
     * @param   newPost     The post to be added to the tree
     * @throws  Exception   The post may already exist
     * */
    public Post insert(Post newPost) throws Exception {

        if (newPost.getTitle().compareTo(title) < 0) {
            // belongs to the left
            if (leftNode == null) {
                leftNode = newPost;
            } else {
                leftNode = leftNode.insert(newPost);
            }
        } else if (newPost.getTitle().compareTo(title) > 0) {
            // belongs to the right
            if (rightNode == null) {
                rightNode = newPost;
            } else {
                rightNode = rightNode.insert(newPost);
            }
        } else {
            throw new Exception("Post already exists");
        }

        // now that the post is inserted, check balance
        int balance = getBalanceFactor();

        if (balance < -1) {
             // some right rotation is in order
            int balanceleft = leftNode.getBalanceFactor();
            if (balanceleft > 0) {
                // left right rotation
                leftNode = leftNode.leftRotation();
                return rightRotation();
            }
            // right rotation
            return rightRotation();
        } else if (balance > 1) {
            // some left rotation is in order
            int balanceright = rightNode.getBalanceFactor();
            if (balanceright < 0) {
                // right left rotation
                rightNode = rightNode.rightRotation();
            }
            // left rotation
            return leftRotation();
        }

        // no rotation required
        return this;
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
