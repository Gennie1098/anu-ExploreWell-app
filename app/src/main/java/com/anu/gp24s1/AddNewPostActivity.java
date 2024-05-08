package com.anu.gp24s1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.state.UserSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddNewPostActivity extends AppCompatActivity {

    private EditText editTextContent, editTextTitle, editTextLocation;
    private CheckBox checkBoxLocation, checkBoxActivity;
    private Button buttonPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextContent = findViewById(R.id.editTextContent);
        checkBoxLocation = findViewById(R.id.checkBoxLocation);
        checkBoxActivity = findViewById(R.id.checkBoxActivity);
        buttonPost = findViewById(R.id.buttonPost);

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        });

    }

    private void submitPost() {
        String title = editTextTitle.getText().toString();
        String location = editTextLocation.getText().toString();
        String content = editTextContent.getText().toString();
        List<String> tags = new ArrayList<>();

        if (checkBoxLocation.isChecked()) {
            tags.add("Location");
        }

        if (checkBoxActivity.isChecked()) {
            tags.add("Activity");
        }

        // Convert List of tags to a single String with commas separating tags
        String tagsString = String.join(", ", tags);

        // pass the single string of tags
        UserSession.getInstance().createPost(title, content, tagsString, location);
    }
}