package com.anu.gp24s1.dao;

import androidx.annotation.NonNull;

import com.anu.gp24s1.pojo.Post;
import com.anu.gp24s1.pojo.User;
import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.utils.DBConnector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PostDaoImpl implements PostDao{

    private PostDaoImpl instance;

    private Post rootPost;

    private HashMap<String,Post> posts;

    private HashMap<String,List<String>> postsGroupByTag;

    private HashMap<String,List<String>> postsGroupsByLocation;

    /**
     * Using singleton design pattern to ensure only get all posts,tags,locations data once.
     * @return instance
     * @author Qinjue Wu
     */
    public PostDaoImpl getInstance() {
        if(instance == null)
        {
            DBConnector connector = new DBConnector().getInstance();
            DatabaseReference postReference = connector.getDatabase().child("post");
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
                        post.setPublishTime(snapshot.child("publishTime").getValue(Date.class));
                        post.setAuthorKey(snapshot.child("authorKey").getValue(String.class));
                        post.setFollowingNumber(snapshot.child("followingNumber").getValue(Integer.class));
                        post.setFollowers((List<String>) snapshot.child("followers").getValue());
                        post.setCommentsNumber(snapshot.child("commentsNumber").getValue(Integer.class));
                        post.setComments((List<String>) snapshot.child("comments").getValue());
                        posts.put(post.getPostKey(),post);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    posts = null;
                }
            });
            DatabaseReference tagReference = connector.getDatabase().child("tag");
            tagReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        postsGroupByTag.put(snapshot.getKey(),(List<String>) snapshot.child("posts").getValue());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    postsGroupByTag = null;
                }
            });
            DatabaseReference locationReference = connector.getDatabase().child("location");
            locationReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        postsGroupsByLocation.put(snapshot.getKey(),(List<String>) snapshot.child("posts").getValue());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    postsGroupsByLocation = null;
                }
            });
        }
        return instance;
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
