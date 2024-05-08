package com.anu.gp24s1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.state.UserSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import kotlin.jvm.internal.Reflection;

public class AddNewPostActivity extends AppCompatActivity {


    private AutoCompleteTextView editTextLocation, editTextTag;
    private EditText editTextContent, editTextTitle;
    private CheckBox checkBoxLocation, checkBoxActivity;
    private Button buttonPost;

    private void addAutocompleteFunctionality() {

        // get tags and locations and convert to an array
        Set<String> tagsSet = PostDaoImpl.getInstance().getAllTags();
        Set<String> locationsSet = PostDaoImpl.getInstance().getAllLocations();
        String[] tags = tagsSet.toArray(new String[0]);
        String[] locations = locationsSet.toArray(new String[0]);

        // adapters
        ArrayAdapter<String> tagArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tags);
        ArrayAdapter<String> locationArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locations);

        editTextTag.setAdapter(tagArrayAdapter);
        editTextLocation.setAdapter(locationArrayAdapter);

        editTextTag.setThreshold(1);
        editTextLocation.setThreshold(1);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextLocation = (AutoCompleteTextView) findViewById(R.id.editTextLocation);
        editTextTag = (AutoCompleteTextView) findViewById(R.id.editTextTag);
        editTextContent = findViewById(R.id.editTextContent);
        buttonPost = findViewById(R.id.buttonPost);

        // Add autocomplete functionality to tags and locations
        addAutocompleteFunctionality();

        // Add error message for title | TODO only if we don't change the allowed titles...


        checkBoxLocation = findViewById(R.id.checkBoxLocation);
        checkBoxActivity = findViewById(R.id.checkBoxActivity);


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
        String tag = editTextTag.getText().toString();
        String content = editTextContent.getText().toString();

        // Add error message for invalid tags and locations
        Set<String> tags = PostDaoImpl.getInstance().getAllTags();
        Set<String> locations = PostDaoImpl.getInstance().getAllLocations();

        boolean validTagAndLocation = true;
        if (!tags.contains(tag)) {
            validTagAndLocation = false;
            editTextTag.setError("Please choose an existing tag");
        }
        if (!locations.contains(location)) {
            validTagAndLocation = false;
            editTextLocation.setError("Please choose an existing location");
        }

        if (validTagAndLocation) { return; }

        // create the new post
        UserSession.getInstance().createPost(title, content, tag, location);
    }
}