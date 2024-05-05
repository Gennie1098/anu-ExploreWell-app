package com.anu.gp24s1.dao;

import androidx.annotation.NonNull;

import com.anu.gp24s1.pojo.Comment;
import com.anu.gp24s1.pojo.User;
import com.anu.gp24s1.pojo.vo.CommentVo;
import com.anu.gp24s1.utils.DBConnector;
import com.anu.gp24s1.utils.TypeConvert;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class CommentDaoImpl implements CommentDao{

    private static CommentDaoImpl instance;

    private static Map<String, List<Comment>> comments;

    private CommentDaoImpl(){}

    /**
     * Using singleton design pattern to ensure only get all comments data once.
     * @return instance
     * @author Qinjue Wu
     */
    public static CommentDaoImpl getInstance() {
        if(instance == null)
        {
            instance = new CommentDaoImpl();
            comments = new HashMap<String, List<Comment>>();
            DatabaseReference commentReference = DBConnector.getInstance().getDatabase().child("comments");
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
                        comment.setCommentTime(TypeConvert.strToDate(snapshot.child("commentTime").getValue(String.class)));
                        String postKey = comment.getPostKey();
                        if(comments.containsKey(postKey)) {
                            List<Comment> commentsList = comments.get(postKey);
                            commentsList.add(comment);
                            comments.put(postKey,commentsList);
                        }
                        else {
                            List<Comment> commentList = new ArrayList<>();
                            commentList.add(comment);
                            comments.put(postKey,commentList);
                        }
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

        // Get current date
        Date publishDate = new Date(); // timezone independent
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));
        String dateString = sdf.format(publishDate);

        // Create a new comment
        Comment newComment = new Comment();
        newComment.setCommentTime(publishDate);
        newComment.setContent(content);
        newComment.setPostKey(postKey);
        newComment.setAuthorKey(userKey);

        // Get database reference
        DatabaseReference dbReference = DBConnector.getInstance().getDatabase();
        HashMap<String, Object> childUpdates = new HashMap<String, Object>();
        String commentKey = dbReference.child("comments").push().getKey();

        // Create Hashmap with comment data:
        HashMap<String, Object> commentValues = new HashMap<String, Object>();
        commentValues.put("content", content);
        commentValues.put("postKey", postKey);
        commentValues.put("authorKey", userKey);
        commentValues.put("commentTime", dateString);

        // Update in database
        childUpdates.put("/comments/" + commentKey, commentValues);

        dbReference.updateChildren(childUpdates);

        // Add key to comment:
        newComment.setCommentKey(commentKey);

        // Add to comments:
        if(comments.containsKey(postKey)) {
            List<Comment> commentsList = comments.get(postKey);
            commentsList.add(newComment);
            comments.put(postKey,commentsList);
        }
        else {
            List<Comment> commentList = new ArrayList<>();
            commentList.add(newComment);
            comments.put(postKey,commentList);
        }
        return commentKey;
    }
}
