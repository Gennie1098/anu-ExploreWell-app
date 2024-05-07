package com.anu.gp24s1.ui.following;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anu.gp24s1.R;
import com.anu.gp24s1.ui.post.PostListFragment;


public class FollowingPostFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_following_post, container, false);

        // Check if the fragment is already added, to avoid overlapping fragments on re-creating the view
        if (getChildFragmentManager().findFragmentById(R.id.following_post_container) == null) {
            PostListFragment postListFragment = PostListFragment.newInstance();
            getChildFragmentManager().beginTransaction()
                    .add(R.id.following_post_container, postListFragment)
                    .commit();
        }

        return view;

    }
}