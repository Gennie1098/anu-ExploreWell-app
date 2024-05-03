package com.anu.gp24s1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.state.UserSession;
import com.anu.gp24s1.ui.PostMultiAutoCompleteTextView;

import java.util.Set;

public class AddNewPostActivity extends AppCompatActivity {

    private PostMultiAutoCompleteTextView editTextContent;
    private EditText editTextTitle, editTextLocation; //TODO: If branch accepted, remove these
    private RadioGroup radioGroupTags;
    private RadioButton radioButtonLocation, radioButtonActivity;
    private Button buttonPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextContent = (PostMultiAutoCompleteTextView) findViewById(R.id.editTextContent);
        radioGroupTags = findViewById(R.id.radioGroupTags);
        buttonPost = findViewById(R.id.buttonPost);

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        });

        // get existing tags and locations:
        Set<String> tagsSet = PostDaoImpl.getInstance().getAllTags();
        Set<String> locationsSet = PostDaoImpl.getInstance().getAllLocations();
        String[] tags = new String[tagsSet.size()];
        String[] locations = new String[locationsSet.size()];

        System.out.println("tags: ");
        int idx = 0;
        for (String tag : tagsSet) {
            tags[idx] = tag;
            System.out.print(tag + ", ");
            idx++;
        }
        System.out.println("locations: ");
        idx = 0;
        for (String location : locationsSet) {
            locations[idx] = location;
            System.out.print(location + ", ");
            idx++;
        }

        // make autocompletions
        String[] allAutocompletions = new String[tags.length + locations.length];
        System.arraycopy(tags, 0, allAutocompletions, 0, tags.length);
        System.arraycopy(locations, 0, allAutocompletions, tags.length, locations.length);

//        String[] allAutocompletions = {"a", "ant", "apple", "asp", "android", "animation", "adobe",
//                "chrome", "chromium", "firefox", "freeware", "fedora"};

        // adapter

        ArrayAdapter<String> autoCompletionArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allAutocompletions);

        editTextContent.setThreshold(1);
        editTextContent.setAdapter(autoCompletionArrayAdapter);
        editTextContent.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


//        editTextContent.setTokenizer(new MultiAutoCompleteTextView.Tokenizer() {
//            @Override
//            public int findTokenStart(CharSequence charSequence, int i) {
//                return 0;
//            }
//
//            @Override
//            public int findTokenEnd(CharSequence charSequence, int i) {
//                return 0;
//            }
//
//            @Override
//            public CharSequence terminateToken(CharSequence charSequence) {
//                return null;
//            }
//        });

//        editTextContent.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

    }

    private void submitPost() {
        String title = editTextTitle.getText().toString();
        String location = editTextLocation.getText().toString();
        String content = editTextContent.getText().toString();
        int selectedId = radioGroupTags.getCheckedRadioButtonId();
        String tag = selectedId == R.id.radioButtonLocation ? "Location" : "Activity";

        UserSession.getInstance().createPost(title, content, tag, location);
    }
}