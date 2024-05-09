package com.anu.gp24s1.ui.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anu.gp24s1.R;
import com.anu.gp24s1.ui.post.PostListFragment;

public class SearchResultFragment extends Fragment {

    SearchViewModel searchViewModel = null;

    public SearchResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        searchViewModel = new ViewModelProvider(requireActivity()).get(SearchViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        // Check if the fragment is already added, to avoid overlapping fragments on re-creating the view
        if (getChildFragmentManager().findFragmentById(R.id.searchResult) == null) {
            PostListFragment postListFragment = PostListFragment.newInstance();
            getChildFragmentManager().beginTransaction()
                    .add(R.id.searchResult, postListFragment)
                    .commit();
        }

        // Add listeners to the searchViewModel data:

        searchViewModel.getTitleState().observe(getViewLifecycleOwner(), title -> {
            // TODO: update the result based on the title:
            // NOTE: title is a String
            Log.d("Debugging", title);

        });

        // Add listeners to the searchViewModel data:
        searchViewModel.getLocationsState().observe(getViewLifecycleOwner(), locations -> {
            // TODO: update the result based on the locations:
            // NOTE: locations is a set
            Log.d("Debugging", Integer.toString(locations.size()));

        });

        // Add listeners to the searchViewModel data:
        searchViewModel.getTagsState().observe(getViewLifecycleOwner(), tags -> {
            // TODO: update the result based on the locations:
            // NOTE: tags is a set
            Log.d("Debugging", Integer.toString(tags.size()));
        });

        return view;
    }
}