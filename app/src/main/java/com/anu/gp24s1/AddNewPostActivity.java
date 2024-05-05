package com.anu.gp24s1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddNewPostActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextLocation, editTextContent;
    private RadioGroup radioGroupTags;
    private RadioButton radioButtonLocation, radioButtonActivity;
    private Button buttonPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextContent = findViewById(R.id.editTextContent);
        radioGroupTags = findViewById(R.id.radioGroupTags);
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
        int selectedId = radioGroupTags.getCheckedRadioButtonId();
        String tag = selectedId == R.id.radioButtonLocation ? "Location" : "Activity";

        // TODO: handle the submission of the post data, possibly sending it to a server or saving locally

    }
}