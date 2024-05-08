package com.anu.gp24s1.ui.following;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.anu.gp24s1.MainActivity;
import com.anu.gp24s1.R;
import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.state.UserSession;
import com.anu.gp24s1.ui.post.PostListFragment;
import com.anu.gp24s1.ui.profile.ProfileFragment;

import java.io.Serializable;
import java.util.List;


public class FollowingPostFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_following_post, container, false);

        // Check if the fragment is already added, to avoid overlapping fragments on re-creating the view
        if (getChildFragmentManager().findFragmentById(R.id.following_post_container) == null) {
            PostListFragment postListFragment = PostListFragment.newInstance();
            String groupName = getArguments().getString("groupName");
            List<PostVo> postVos = UserSession.getInstance().viewFollowingPosts(groupName);
            Bundle bundle = new Bundle();
            bundle.putSerializable("postVoList",(Serializable) postVos);
            postListFragment.setArguments(bundle);
            getChildFragmentManager().beginTransaction()
                    .add(R.id.following_post_container, postListFragment)
                    .commit();
        }

        ImageView backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if it's possible to go back and then go back
                if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                    getParentFragmentManager().popBackStack();
                }
            }
        });

        return view;

    }
}