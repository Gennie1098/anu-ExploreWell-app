package com.anu.gp24s1.ui.post;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.anu.gp24s1.R;
import com.anu.gp24s1.dao.CommentDaoImpl;
import com.anu.gp24s1.pojo.vo.CommentVo;
import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.state.UserSession;
import com.anu.gp24s1.utils.DBConnector;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class SinglePostActivity extends AppCompatActivity {

    EditText addCommentText;
    Button addCommentButton;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SinglePostActivity", "Back button clicked");
                finish();
            }
        });

        recyclerView = findViewById(R.id.commentSection);

        // Retrieve the data from intent
        String postKey = getIntent().getStringExtra("post_details");
        updatePost(postKey);

        CommentAdapter adapter = new CommentAdapter(this, commentModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add comment:
        addCommentText = findViewById(R.id.addCommentText);
        addCommentButton = findViewById(R.id.addCommentButton);

        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSession.getInstance().addComment(postKey, addCommentText.getText().toString());
            }
        });

        // follow
        ImageButton followingButton = findViewById(R.id.followingButton);
        followingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = UserSession.getInstance().followPost(postKey);
                System.out.println("add follow result: "+result);
            }
        });

    }

    ArrayList<CommentVo> commentModels = new ArrayList<>();

    /**
     *
     * @param post
     * @author u7776887 Qinjue Wu
     */
    private void setUpCommentModels (PostVo post) {
        commentModels.clear();
        if(post.getComments() != null && post.getComments().size() !=0)
        {
            commentModels.addAll(post.getComments());
        }
    }

    private void updateUI(PostVo post) {
        // Assuming you have these views in your layout
        ImageView userImg = findViewById(R.id.userImg);
        TextView UserName = findViewById(R.id.UserName);
        TextView time = findViewById(R.id.time);
        TextView PostTitle = findViewById(R.id.PostTitle);
        Chip locationTag = findViewById(R.id.locationTag);
        Chip activityTag = findViewById(R.id.activityTag);
        TextView postContent = findViewById(R.id.content);
        TextView numberFollowing = findViewById(R.id.numberFollowing);
        TextView numberComments = findViewById(R.id.numberComments);

        UserName.setText(post.getAuthorName());
        Uri imageUrl = Uri.parse(post.getAuthorAvatar());
        Picasso.get().load(imageUrl)
                .error(R.drawable.default_avatar_profile)
                .into(userImg);
        time.setText(post.getPublishTime().toString());
        PostTitle.setText(post.getTitle());
        locationTag.setText(post.getLocation());
        activityTag.setText(post.getTag());
        postContent.setText(post.getContent());
        numberFollowing.setText(String.valueOf(post.getFollowerNumber()));
        numberComments.setText(String.valueOf(post.getCommentsNumber()));
    }

    private void updatePost(String postKey) {
        DatabaseReference postDatabase = DBConnector.getInstance().getDatabase().child("post");
        postDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PostVo post = UserSession.getInstance().viewPost(postKey);
                updateUI(post);
                setUpCommentModels(post);
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}