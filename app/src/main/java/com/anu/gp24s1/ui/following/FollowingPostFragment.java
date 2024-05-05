package com.anu.gp24s1.ui.following;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anu.gp24s1.R;
import com.anu.gp24s1.ui.post.PostListFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FollowingPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowingPostFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FollowingPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowingPostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowingPostFragment newInstance(String param1, String param2) {
        FollowingPostFragment fragment = new FollowingPostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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