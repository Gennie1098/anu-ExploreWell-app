package com.anu.gp24s1.dao;

import androidx.annotation.NonNull;

import com.anu.gp24s1.pojo.Post;
import com.anu.gp24s1.pojo.User;
import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.utils.DBConnector;
import com.anu.gp24s1.utils.TypeConvert;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PostDaoImpl implements PostDao {

    private static final int RECOMMENDATION_NUMBER = 3;
    private static PostDaoImpl instance;

    private Post rootPost;

    private static HashMap<String, Post> posts;

    private static HashMap<String, List<String>> postsGroupByTag;

    private static HashMap<String,List<String>> postsGroupByLocation;

    private PostDaoImpl(){};

    /**
     * Using singleton design pattern to ensure only get all posts,tags,locations data once.
     * @return instance PostDaoImpl
     * @author Qinjue Wu
     */
    public static PostDaoImpl getInstance() {
        if(instance == null)
        {
            instance = new PostDaoImpl();
            posts = new HashMap<String,Post>();
            DatabaseReference postReference = DBConnector.getInstance().getDatabase().child("post");
            postReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        //create new User object and set values then put it into the hashmap
                        Post post = new Post();
                        post.setPostKey(snapshot.getKey());
                        post.setTitle(snapshot.child("title").getValue(String.class));
                        post.setContent(snapshot.child("content").getValue(String.class));
                        post.setTag(snapshot.child("tag").getValue(String.class));
                        post.setLocation(snapshot.child("location").getValue(String.class));
                        post.setPublishTime(TypeConvert.strToDate(snapshot.child("publishTime").getValue(String.class)));
                        post.setAuthorKey(snapshot.child("authorKey").getValue(String.class));
                        post.setFollowerNumber(snapshot.child("followerNumber").getValue(Integer.class));
                        HashMap<String,Boolean> followersMap = (HashMap<String,Boolean>) snapshot.child("followers").getValue();
                        if(followersMap != null)
                        {
                            List<String> followersList = new ArrayList<>(followersMap.keySet());
                            post.setFollowers(followersList);
                        }
                        post.setCommentsNumber(snapshot.child("commentsNumber").getValue(Integer.class));
                        HashMap<String,Boolean> commentsMap = (HashMap<String,Boolean>) snapshot.child("comments").getValue();
                        if(commentsMap != null)
                        {
                            List<String> commentsList = new ArrayList<>(commentsMap.keySet());
                            post.setComments(commentsList);
                        }
                        posts.put(post.getPostKey(),post);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    posts = null;
                }
            });
            DatabaseReference tagReference = DBConnector.getInstance().getDatabase().child("tag");
            tagReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        HashMap<String,Boolean> postsMap = (HashMap<String,Boolean>) snapshot.child("posts").getValue();
                        if(postsMap != null)
                        {
                            List<String> postsList = new ArrayList<>(postsMap.keySet());
                            postsGroupByTag.put(snapshot.getKey(),postsList);
                        }
                        else
                        {
                            postsGroupByTag.put(snapshot.getKey(),null);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    postsGroupByTag = null;
                }
            });
            DatabaseReference locationReference = DBConnector.getInstance().getDatabase().child("location");
            locationReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        HashMap<String,Boolean> postsMap = (HashMap<String,Boolean>) snapshot.child("posts").getValue();
                        if(postsMap != null)
                        {
                            List<String> postsList = new ArrayList<>(postsMap.keySet());
                            postsGroupByLocation.put(snapshot.getKey(),postsList);
                        }
                        else
                        {
                            postsGroupByLocation.put(snapshot.getKey(),null);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    postsGroupByLocation = null;
                }
            });
        }
        return instance;
    }

    /**
     * Given the passion tag of the user, and recommend corresponding posts to users by the number of followers.
     * @param tag String
     * @return List<Post>
     * @author Qinjue Wu
     */
    @Override
    public List<Post> getRecommendationByTag(String tag) {
        if(postsGroupByTag.containsKey(tag))
        {
            List<String> postKeyList = postsGroupByTag.get(tag);
            if(postKeyList == null)
            {
                return null;
            }
            List<Post> sortPosts = sortPostsByfollowerNum(getPostList(postKeyList));
            int postsNum = sortPosts.size();
            if(RECOMMENDATION_NUMBER < postsNum)
            {
                return sortPosts.subList(0,RECOMMENDATION_NUMBER);
            }
            else if(postsNum == 0)
            {
                return null;
            }
            else
            {
                return sortPosts.subList(0,postsNum);
            }
        }
        return null;
    }

    /**
     * Given the location of the user, and recommend corresponding posts to users by the number of followers.
     * @param location String
     * @return List<Post>
     * @author Qinjue Wu
     */
    @Override
    public List<Post> getRecommendationByLocation(String location) {
        if(postsGroupByLocation.containsKey(location))
        {
            List<String> postKeyList = postsGroupByLocation.get(location);
            if(postKeyList == null)
            {
                return null;
            }
            List<Post> sortPosts = sortPostsByfollowerNum(getPostList(postKeyList));
            int postsNum = sortPosts.size();
            if(RECOMMENDATION_NUMBER < postsNum)
            {
                return sortPosts.subList(0,RECOMMENDATION_NUMBER);
            }
            else if(postsNum == 0)
            {
                return null;
            }
            else
            {
                return sortPosts.subList(0,postsNum);
            }
        }
        return null;
    }

    @Override
    public String createPost(String title, String content, String tag, String location, String userKey) {
        return null;
    }

    /**
     * Record the actions of users following posts by user key and post key
     * @param postKey
     * @param userKey
     * @return whether the operation is successful or not
     * @author  u7793565    Qihua Huang
     * */
    @Override
    public boolean followPost(String postKey, String userKey) {
        PostVo postVo = viewPost(postKey, userKey);
        Post post = posts.get(postKey);
        assert post != null;
        List<String> followersList = post.getFollowers();
        int followerNumber = post.getFollowerNumber();

        UserDao userDao = UserDaoImpl.getInstance();

        // already followed
        if (postVo.isFollowing()) {
            return true;
        } else {
            //Add the user to the post follower list
            postVo.setFollowing(true);
            boolean addFollowerListResult = followersList.add(userKey);
            post.setFollowers(followersList);

            //Update number of followers
            followerNumber++;
            post.setFollowerNumber(followerNumber);

            //Update database data
            // TODO: this implementation is not sure
            DatabaseReference postReference = DBConnector.getInstance().getDatabase().child("post");
            postReference.child("users").child(postKey).child("followerNumber").setValue((long) followerNumber);
            postReference.child("users").child(postKey).child("followers").setValue(followersList);

            //Add the post to the user's following list
            boolean addFollowingListResult = userDao.addFollowingPost(userKey, postKey);
            return addFollowerListResult && addFollowingListResult;
        }
    }

    /**
     * Retrieve the information of a post according to the postkey
     * and convert it into a post instance using post.toPostVo
     *
     * @return the PostVo object containing the details of the post, or null
     * @author u7793565    Qihua Huang
     */
    @Override
    public PostVo viewPost(String postKey, String userKey) {
        Post post = posts.get(postKey);
        if (post == null) {
            // if post or key does not exist
            throw new IllegalArgumentException("Post with key " + postKey + " does not exist.");
        }
        return post.toPostVo(userKey);
    }

    @Override
    public void addComment(String commentKey, String postKey) {

    }

    /**
     * Given user's following posts' keys,
     * and return groups of these posts by tags and locations order alphabetically.
     * @param postKeyList List<String>
     * @return List<String>
     * @author Qinjue Wu
     */
    @Override
    public List<String> getGroupsOfPosts(List<String> postKeyList) {
        Set<String> groups = new HashSet<>();
        for(String postKey : postKeyList)
        {
            if(posts.containsKey(postKey))
            {
                groups.add(Objects.requireNonNull(posts.get(postKey)).getTag());
                groups.add(Objects.requireNonNull(posts.get(postKey)).getLocation());
            }
        }
        List<String> groupList =  new ArrayList<>(groups);
        Comparator<String> stringComparator = new Comparator<>() {
            @Override
            public int compare(String s, String t1) {
                return Character.compare(s.charAt(0), t1.charAt(0));
            }
        };
        groupList.sort(stringComparator);
        return groupList;
    }

    /**
     * Given the name of group
     * and return a list of posts followed by the user and belong to the group.
     * @param group String
     * @return List<Post>
     * @author Qinjue Wu
     */
    @Override
    public List<Post> getFollowingPostsByGroup(String group, List<String> postKeyList) {
        if(postsGroupByTag.containsKey(group)) {
            List<String> intersection = postsGroupByTag.get(group);
            assert intersection != null;
            intersection.retainAll(postKeyList);
            return getPostList(intersection);
        }
        else if (postsGroupByLocation.containsKey(group)) {
            List<String> intersection = postsGroupByLocation.get(group);
            assert intersection != null;
            intersection.retainAll(postKeyList);
            return getPostList(intersection);
        }
        return null;
    }

    @Override
    public List<Post> searchPosts(String searchWords) {
        return null;
    }

    /**
     * Convert a list of Post objects to PostVo objects
     * @param postsList List<Post>
     * @param userKey String
     * @return List<PostVo>
     * @author Qinjue Wu
     */
    @Override
    public List<PostVo> viewListOfPosts(List<Post> postsList, String userKey) {
        List<PostVo> postVoList = new ArrayList<>();
        for (Post post : postsList) {
            postVoList.add(post.toPostVo(userKey));
        }
        return postVoList;
    }

    /**
     * From a list of keys of posts to get corresponding post objetcs.
     * @param postKeyList List<String>
     * @return postsList List<Post>
     * @author Qinjue Wu
     */
    public List<Post> getPostList(List<String> postKeyList) {
        List<Post> postsList = new ArrayList<>();
        for(String postKey : postKeyList)
        {
            if(posts.containsKey(postKey))
            {
                postsList.add(posts.get(postKey));
            }
        }
        return postsList;
    }

    /**
     * Sort a lists of posts according to the number of their followers in descending order.
     * @param posts List<Post>
     * @return posts List<Post>
     * @author Qinjue Wu
     */
    public List<Post> sortPostsByfollowerNum(List<Post> posts) {
        Comparator<Post> comparatorPost = new Comparator<>() {
            @Override
            public int compare(Post o1, Post o2) {
                return o2.getFollowerNumber() - o1.getFollowerNumber();
            }
        };
        posts.sort(comparatorPost);
        return posts;
    }
}
