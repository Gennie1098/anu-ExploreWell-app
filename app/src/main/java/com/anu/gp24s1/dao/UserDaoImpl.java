package com.anu.gp24s1.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.anu.gp24s1.pojo.User;
import com.anu.gp24s1.pojo.vo.UserVo;
import com.anu.gp24s1.utils.DBConnector;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class UserDaoImpl implements UserDao {

    private static UserDaoImpl instance;

    private static HashMap<String, User> users;

    private UserDaoImpl() {
    }

    ;

    /**
     * Using singleton design pattern to ensure only get all users' information data once.
     *
     * @return instance
     * @author Qinjue Wu
     */
    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
            users = new HashMap<String, User>();
            DatabaseReference userReference = DBConnector.getInstance().getDatabase().child("user");
            userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        //create new User object and set values then put it into the hashmap
                        User user = new User();
                        user.setUserKey(snapshot.getKey());
                        user.setUsername(snapshot.child("username").getValue(String.class));
                        user.setAvatar(snapshot.child("avatar").getValue(String.class));
                        user.setLocation(snapshot.child("location").getValue(String.class));
                        user.setPassion(snapshot.child("passion").getValue(String.class));
                        HashMap<String, Boolean> ownPostsMap = (HashMap<String, Boolean>) snapshot.child("ownPosts").getValue();
                        if (ownPostsMap != null) {
                            List<String> ownPostsList = new ArrayList<>(ownPostsMap.keySet());
                            user.setOwnPosts(ownPostsList);
                        }
                        HashMap<String, Boolean> followingPostsMap = (HashMap<String, Boolean>) snapshot.child("followingPosts").getValue();
                        if (followingPostsMap != null) {
                            List<String> followingPostsList = new ArrayList<>(followingPostsMap.keySet());
                            user.setFollowingPosts(followingPostsList);
                        }
                        users.put(user.getUserKey(), user);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    users = null;
                }

            });
        }
        return instance;
    }

    /**
     * Using userKey to get username from the hashmap.
     *
     * @param userKey
     * @return username
     * @author Qinjue Wu
     */
    @Override
    public String getUsername(String userKey) {
        if (users != null && users.containsKey(userKey)) {
            return users.get(userKey).getUsername();
        } else {
            return null;
        }
    }

    /**
     * Using userKey to get user's avatar from the hashmap.
     *
     * @param userKey
     * @return avatar
     * @author Qinjue Wu
     */
    @Override
    public String getAvatar(String userKey) {
        if (users != null && users.containsKey(userKey)) {
            return users.get(userKey).getAvatar();
        } else {
            return null;
        }
    }

    /**
     * Using userKey to get user's passion from the hashmap.
     *
     * @param userKey
     * @return passion
     * @author Qinjue Wu
     */
    @Override
    public String getPassion(String userKey) {
        if (users != null) {
            return users.get(userKey).getPassion();
        } else {
            return null;
        }
    }

    /**
     * Using userKey to get user's location from the hashmap.
     *
     * @param userKey
     * @return location
     * @author Qinjue Wu
     */
    @Override
    public String getLocation(String userKey) {
        if (users != null) {
            return users.get(userKey).getLocation();
        } else {
            return null;
        }
    }

    /**
     * Retrieve the information of user according to the user key
     * and convert it into a user instance using user.toUserVo
     *
     * @return the UserVo object containing the details of the user, or null
     * @author u7793565    Qihua Huang
     */
    @Override
    public UserVo getProfile(String userKey) {
        if (users != null) {
            User user = users.get(userKey);
            if (user == null) {
                // if post or key does not exist
                throw new IllegalArgumentException("User with key " + userKey + " does not exist.");
            }
            return user.toUserVo();
        } else {
            return null;
        }
    }

    @Override
    public boolean addOwnPost(String userKey, String postKey) {
        return false;
    }

    /**
     * Add a post to the user's following list the given post key and user key
     * @param userKey
     * @param postKey
     * @return whether the operation is successful or not
     * @author  u7793565    Qihua Huang
     * */
    @Override
    public boolean addFollowingPost(String userKey, String postKey) {
        User user = users.get(userKey);
        assert user != null;
        List<String> followingPosts = user.getFollowingPosts();

        // already followed
        if (followingPosts.contains(postKey)) {
            return true;
        } else {
            //Add the post to the user's following list
            return followingPosts.add(postKey);
        }
    }

    @Override
    public List<String> getFollowingPosts(String userKey) {
        return null;
    }
}
