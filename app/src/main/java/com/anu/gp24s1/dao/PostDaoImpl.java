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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PostDaoImpl implements PostDao{

    private static PostDaoImpl instance;

    private Post rootPost;

    private static HashMap<String,Post> posts;

    private static HashMap<String,List<String>> postsGroupByTag;

    private static HashMap<String,List<String>> postsGroupsByLocation;

    private PostDaoImpl(){};

    /**
     * Using singleton design pattern to ensure only get all posts,tags,locations data once.
     * @return instance
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
                            postsGroupsByLocation.put(snapshot.getKey(),postsList);
                        }
                        else
                        {
                            postsGroupsByLocation.put(snapshot.getKey(),null);
                        }
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
