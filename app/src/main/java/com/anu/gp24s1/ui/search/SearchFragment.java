package com.anu.gp24s1.ui.search;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.anu.gp24s1.MainActivity;
import com.anu.gp24s1.R;
import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.databinding.FragmentHomeBinding;
import com.anu.gp24s1.databinding.FragmentSearchBinding;

import java.util.Set;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;

    FragmentSearchBinding searchBinding;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        searchBinding = FragmentSearchBinding.inflate(inflater, container, false);

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Add listeners to the searchViewModel data:
        searchViewModel.getTitleState().observe(getViewLifecycleOwner(), title -> {
            // TODO: update the result based on the title:
            // NOTE: title is a String


        });

        // Add listeners to the searchViewModel data:
        searchViewModel.getLocationsState().observe(getViewLifecycleOwner(), locations -> {
            // TODO: update the result based on the locations:
            // NOTE: locations is a set

        });

        // Add listeners to the searchViewModel data:
        searchViewModel.getTagsState().observe(getViewLifecycleOwner(), tags -> {
            // TODO: update the result based on the locations:
            // NOTE: tags is a set

        });

        // get existing tags and locations:
        Set<String> tagsSet = PostDaoImpl.getInstance().getAllTags();
        Set<String> locationsSet = PostDaoImpl.getInstance().getAllLocations();
        String[] tags = new String[tagsSet.size()];
        String[] locations = new String[locationsSet.size()];

        int idx = 0;
        for (String tag : tagsSet) {
            tags[idx] = "#" + tag;
            idx++;
        }
        idx = 0;
        for (String location : locationsSet) {
            locations[idx] = "@" + location;
            idx++;
        }

        // make autocompletions
        String[] allAutocompletions = new String[tags.length + locations.length];
        System.arraycopy(tags, 0, allAutocompletions, 0, tags.length);
        System.arraycopy(locations, 0, allAutocompletions, tags.length, locations.length);

        // adapter
        ArrayAdapter<String> autoCompletionArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, allAutocompletions);

        MultiAutoCompleteTextView searchContent = (MultiAutoCompleteTextView) view.findViewById(R.id.searchContent);

        searchContent.setThreshold(1);
        searchContent.setAdapter(autoCompletionArrayAdapter);
        searchContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                        searchViewModel.updateSearch(textView.toString());
                    }
                }
                return false;
            }
        });


        /**
         * Based on the CommaTokenizer in:
         * @see https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/widget/MultiAutoCompleteTextView.java
         *
         * */
        searchContent.setTokenizer(new MultiAutoCompleteTextView.Tokenizer() {
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

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Update the title of the activity when the fragment is resumed
        ((MainActivity) requireActivity()).updateTitle("Search");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}