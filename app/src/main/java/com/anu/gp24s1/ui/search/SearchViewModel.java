package com.anu.gp24s1.ui.search;

import android.util.SparseArray;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anu.gp24s1.pojo.Post;
import com.anu.gp24s1.utils.PostTokenizer;
import com.anu.gp24s1.utils.SearchParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchViewModel extends ViewModel {

    private final MutableLiveData<Set<String>> tagsState =
            new MutableLiveData<>(new HashSet<String>());

    private final MutableLiveData<Set<String>> locationsState =
            new MutableLiveData<>(new HashSet<String>());

    private final MutableLiveData<Boolean> validState =
            new MutableLiveData<>(Boolean.TRUE);

    private final MutableLiveData<String> titleState =
            new MutableLiveData<>("");

    public SearchViewModel() {
        // ??
    }

    public void updateSearch(String searchString) {
        PostTokenizer tokenizer = new PostTokenizer(searchString);
        SearchParser parser = new SearchParser(tokenizer);
        boolean valid = parser.parseX();
        if (Boolean.logicalXor(valid, Boolean.TRUE.equals(validState.getValue()))) {
            validState.setValue(valid);
        }
        if (!parser.getTags().equals(tagsState.getValue())) {
            tagsState.setValue(parser.getTags());
        }
        if (!parser.getLocations().equals(locationsState.getValue())) {
            locationsState.setValue(parser.getLocations());
        }
        if (!parser.getTitle().equals(titleState.getValue())) {
            titleState.setValue(parser.getTitle());
        }
    }


    public LiveData<Set<String>> getTagsState() {
        return tagsState;
    }

    public LiveData<Set<String>> getLocationsState() {
        return locationsState;
    }

    public LiveData<String> getTitleState() {
        return titleState;
    }

    public LiveData<Boolean> getValidState() {
        return validState;
    }

}