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

import java.util.Set;

public class AddNewPostActivity extends AppCompatActivity {

    private MultiAutoCompleteTextView editTextContent;
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
        editTextContent = (MultiAutoCompleteTextView) findViewById(R.id.editTextContent);
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
            tags[idx] = "#" + tag;
            System.out.print(tag + ", ");
            idx++;
        }
        System.out.println("locations: ");
        idx = 0;
        for (String location : locationsSet) {
            locations[idx] = "@" + location;
            System.out.print(location + ", ");
            idx++;
        }

        // make autocompletions
        String[] allAutocompletions = new String[tags.length + locations.length];
        System.arraycopy(tags, 0, allAutocompletions, 0, tags.length);
        System.arraycopy(locations, 0, allAutocompletions, tags.length, locations.length);

        // adapter
        ArrayAdapter<String> autoCompletionArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allAutocompletions);

        editTextContent.setThreshold(1);
        editTextContent.setAdapter(autoCompletionArrayAdapter);
        //editTextContent.listene


        /**
         * Based on the CommaTokenizer in:
         * @see https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/widget/MultiAutoCompleteTextView.java
         *
         * */
        editTextContent.setTokenizer(new MultiAutoCompleteTextView.Tokenizer() {
            @Override
            public int findTokenStart(CharSequence inputText, int cursor) {

                int idx = cursor;

                while (idx > 0 && inputText.charAt(idx - 1) != '@' && inputText.charAt(idx - 1) != '#') {
                    idx--;
                }
                if (idx > 0) {idx--;} // we want the @ or # symbol in the token

                return idx;
            }

            @Override
            public int findTokenEnd(CharSequence inputText, int cursor) {
                int idx = cursor;

                while (idx < inputText.length() && Character.isLetter(inputText.charAt(idx))) {
                    idx++;
                }

                return idx;
            }

            @Override
            public CharSequence terminateToken(CharSequence inputText) {
                return inputText;
//                int idx = inputText.length();
//
//                while (idx > 0 && inputText.charAt(idx - 1) == ' ') {
//                    idx--;
//                }
//                if (idx > 0 && inputText.charAt(idx - 1) == ' ') {
//                    return inputText;
//                } else {
//                    if (inputText instanceof Spanned) {
//                        SpannableString sp = new SpannableString(inputText + " ");
//                        TextUtils.copySpansFrom((Spanned) inputText, 0, inputText.length(),
//                                Object.class, sp, 0);
//                        return sp;
//                    } else {
//                        return inputText + " ";
//                    }
//                }
            }
        });

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