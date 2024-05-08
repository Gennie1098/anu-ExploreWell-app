package com.anu.gp24s1.ui.post;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.anu.gp24s1.R;
import com.anu.gp24s1.pojo.vo.CommentVo;
import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.state.UserSession;
import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class SinglePostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);

        RecyclerView recyclerView = findViewById(R.id.commentSection);

        // Retrieve the data from intent
        PostVo post = (PostVo) getIntent().getSerializableExtra("post_details");
        if (post != null) {
            updateUI(post);
            setUpCommentModels (post);
        }

        CommentAdapter adapter = new CommentAdapter(this, commentModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    ArrayList<CommentVo> commentModels = new ArrayList<>();

    /**
     *
     * @param post
     * @author u7776887 Qinjue Wu
     */
    private void setUpCommentModels (PostVo post) {
        commentModels.clear();
        //TODO remove test data
        CommentVo commentVo = new CommentVo();
        commentVo.setUsername("test!");
        commentVo.setContent("for test");
        commentVo.setCommentTime(new Date(System.currentTimeMillis()));
        commentVo.setUserAvatar("https://robohash.org/numquamquoscorporis.png?size=50x50&set=set1");
        commentModels.add(commentVo);
//        if(post.getComments() == null || post.getComments().size() ==0)
//        {
//            commentModels.addAll(post.getComments());
//        }
    }

    private void updateUI(PostVo post) {
        // Assuming you have these views in your layout
        // TODO: update UI with data of post
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
}