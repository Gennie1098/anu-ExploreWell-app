package com.anu.gp24s1.ui.post;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.anu.gp24s1.R;

import java.util.ArrayList;

public class SinglePostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);

        RecyclerView recyclerView = findViewById(R.id.commentSection);
        setUpCommentModels ();
        CommentAdapter adapter = new CommentAdapter(this, commentModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve the data from intent
        PostListModel post = (PostListModel) getIntent().getSerializableExtra("post_details");

        if (post != null) {
            updateUI(post);
        }
    }

    ArrayList<CommentModel> commentModels = new ArrayList<>();
    int[] userImg = {R.drawable.ic_outline_account_circle_24, R.drawable.ic_outline_account_circle_24, R.drawable.ic_outline_account_circle_24};

    private void setUpCommentModels () {
        String[] userName = getResources().getStringArray(R.array.example_user_name_list_txt);
        String[] time = getResources().getStringArray(R.array.example_user_name_list_txt);
        String[] content = getResources().getStringArray(R.array.exapmle_post_titles_list_txt);
        for (int i = 0; i < userName.length; i++) { // arrays should be equal length
            commentModels.add(new CommentModel(userImg[i], userName[i], time[i], content[i]));
        }
    }

    private void updateUI(PostListModel post) {
        // Assuming you have these views in your layout
        // TODO: update UI with data of post

    }
}