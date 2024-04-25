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

import java.util.HashMap;
import java.util.List;

public class UserDaoImpl implements UserDao{

    private UserDaoImpl instance;

    private HashMap<String, User> users;

    /**
     * Using singleton design pattern to ensure only get all users' information data once.
     * @return instance
     * @author Qinjue Wu
     */
    public UserDaoImpl getInstance() {
        if(instance == null)
        {
            DBConnector connector = new DBConnector().getInstance();
            DatabaseReference userReference = connector.getDatabase().child("users");
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
                        user.setOwnPosts((List<String>) snapshot.child("ownPosts").getValue());
                        user.setFollowingPosts((List<String>) snapshot.child("followingPosts").getValue());
                        users.put(user.getUserKey(),user);
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
     * @param userKey
     * @return username
     * @author Qinjue Wu
     */
    @Override
    public String getUsername(String userKey) {
        if(users != null)
        {
            return users.get(userKey).getUsername();
        }
        else
        {
            return null;
        }
    }

    /**
     * Using userKey to get user's avatar from the hashmap.
     * @param userKey
     * @return avatar
     * @author Qinjue Wu
     */
    @Override
    public String getAvatar(String userKey) {
        if(users != null)
        {
            return users.get(userKey).getAvatar();
        }
        else
        {
            return null;
        }
    }

    /**
     * Using userKey to get user's passion from the hashmap.
     * @param userKey
     * @return passion
     * @author Qinjue Wu
     */
    @Override
    public String getPassion(String userKey) {
        if(users != null)
        {
            return users.get(userKey).getPassion();
        }
        else
        {
            return null;
        }
    }

    /**
     * Using userKey to get user's location from the hashmap.
     * @param userKey
     * @return location
     * @author Qinjue Wu
     */
    @Override
    public String getLocation(String userKey) {
        if(users != null)
        {
            return users.get(userKey).getLocation();
        }
        else
        {
            return null;
        }
    }

    @Override
    public UserVo getProfile(String userKey) {
        return null;
    }

    @Override
    public boolean addOwnPost(String userKey, String postKey) {
        return false;
    }

    @Override
    public boolean addFollowingPost(String userKey, String postKey) {
        return false;
    }

    @Override
    public List<String> getFollowingPosts(String userKey) {
        return null;
    }
}
