package com.anu.gp24s1.ui.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anu.gp24s1.R;
import com.anu.gp24s1.pojo.User;
import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.pojo.vo.UserVo;
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

public class YourPostFragment extends Fragment {

    private PostListViewModel ownPostViewModel;

    public YourPostFragment() {
        // Required empty public constructor
    }


//    public static YourPostFragment newInstance(String param1, String param2) {
//        YourPostFragment fragment = new YourPostFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_following_post, container, false);
        View view = inflater.inflate(R.layout.fragment_your_post, container, false);

        ownPostViewModel = new ViewModelProvider(requireActivity()).get(PostListViewModel.class);
        setOwnPostViewModel();

        // Check if the fragment is already added, to avoid overlapping fragments on re-creating the view
        if (getChildFragmentManager().findFragmentById(R.id.your_post_container) == null) {
            PostListFragment postListFragment = PostListFragment.newInstance();
            getChildFragmentManager().beginTransaction()
                    .add(R.id.your_post_container, postListFragment)
                    .commit();
        }

        return view;

    }

    public void setOwnPostViewModel()
    {
        UserVo uservo = UserSession.getInstance().getProfile();
        List<PostVo> postVos = uservo.getOwnPosts();
        ownPostViewModel.setPostVoListData(postVos);
    }

}