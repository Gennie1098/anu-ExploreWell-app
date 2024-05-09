package com.anu.gp24s1.ui.following;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.anu.gp24s1.R;
import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.state.UserSession;
import com.anu.gp24s1.ui.post.PostListFragment;
import com.anu.gp24s1.ui.post.PostListViewModel;
import com.anu.gp24s1.utils.DBConnector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.List;


public class FollowingPostFragment extends Fragment {

    private PostListViewModel followingViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_following_post, container, false);

        followingViewModel = new ViewModelProvider(requireActivity()).get(PostListViewModel.class);
        setFollowingViewModel();
        updatePostList();

        // Check if the fragment is already added, to avoid overlapping fragments on re-creating the view
        if (getChildFragmentManager().findFragmentById(R.id.following_post_container) == null) {
            PostListFragment postListFragment = PostListFragment.newInstance();
            getChildFragmentManager().beginTransaction()
                    .add(R.id.following_post_container, postListFragment)
                    .commit();
        }

        ImageView backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use getParentFragmentManager() instead of the deprecated getFragmentManager()
                if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                    getParentFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }

    public void setFollowingViewModel()
    {
        String groupName = getArguments().getString("groupName");
        List<PostVo> postVos = UserSession.getInstance().viewFollowingPosts(groupName);
        followingViewModel.setPostVoListData(postVos);
    }

    public void updatePostList()
    {
        DatabaseReference postDatabase = DBConnector.getInstance().getDatabase().child("post");

        postDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setFollowingViewModel();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}