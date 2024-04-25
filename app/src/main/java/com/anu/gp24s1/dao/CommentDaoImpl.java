package com.anu.gp24s1.dao;

import androidx.annotation.NonNull;

import com.anu.gp24s1.pojo.Comment;
import com.anu.gp24s1.pojo.vo.CommentVo;
import com.anu.gp24s1.utils.DBConnector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommentDaoImpl implements CommentDao{

    private CommentDaoImpl instance;

    private Map<String, Comment> comments;

    /**
     * Using singleton design pattern to ensure only get all comments data once.
     * @return instance
     * @author Qinjue Wu
     */
    public CommentDaoImpl getInstance() {
        if(instance == null)
        {
            DBConnector connector = new DBConnector().getInstance();
            DatabaseReference commentReference = connector.getDatabase().child("comments");
            commentReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {
                        Comment comment = new Comment();
                        comment.setCommentKey(snapshot.getKey());
                        comment.setPostKey(snapshot.child("postKey").getValue(String.class));
                        comment.setAuthorKey(snapshot.child("authorKey").getValue(String.class));
                        comment.setContent(snapshot.child("content").getValue(String.class));
                        comment.setCommentTime(snapshot.child("commentTime").getValue(Date.class));
                        comments.put(comment.getCommentKey(),comment);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    comments = null;
                }
            });

        }
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
