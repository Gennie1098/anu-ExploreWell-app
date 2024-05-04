package com.anu.gp24s1.ui.post;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anu.gp24s1.R;
import com.anu.gp24s1.databinding.FragmentHomeBinding;
import com.anu.gp24s1.databinding.FragmentPostListBinding;
import com.anu.gp24s1.ui.home.HomeViewModel;
import com.anu.gp24s1.ui.home.RePostsByLocationAdapter;

import java.util.ArrayList;

public class PostListFragment extends Fragment {

    private PostListViewModel mViewModel;

    private FragmentPostListBinding binding;

    public static PostListFragment newInstance() {
        return new PostListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentPostListBinding.inflate(inflater, container, false);

        //list by locations
        RecyclerView recyclerViewLocation = binding.postList;
        setUpPostListModels();

        PostListAdapter adapter = new PostListAdapter(getActivity(), postListModels);
        adapter.setOnItemClickListener(this::handleItemClick);
        recyclerViewLocation.setAdapter(adapter);
        recyclerViewLocation.setLayoutManager(new LinearLayoutManager(getActivity()));

        View root = binding.getRoot();
        return root;
    }

    private void handleItemClick(PostListModel item) {
        Intent intent = new Intent(getActivity(), SinglePostActivity.class);
        intent.putExtra("post_details", item); // Ensure PostListModel is Serializable or Parcelable
        startActivity(intent);
    }

    ArrayList<PostListModel> postListModels = new ArrayList<>();
    int[] userAva = {R.drawable.ic_outline_account_circle_24, R.drawable.ic_outline_account_circle_24, R.drawable.ic_outline_account_circle_24};

    //TODO: Set up group data
    // check this video: https://www.youtube.com/watch?v=Mc0XT58A1Z4
    private void setUpPostListModels() {
        String[] userName = getResources().getStringArray(R.array.example_user_name_list_txt);
        String[] time = getResources().getStringArray(R.array.example_user_name_list_txt);
        String[] PostTitle = getResources().getStringArray(R.array.exapmle_post_titles_list_txt);
        String[] locationTag = getResources().getStringArray(R.array.exapmle_location_list_txt);
        String[] activityTag = getResources().getStringArray(R.array.exapmle_activities_list_txt);
        String[] postContent = getResources().getStringArray(R.array.exapmle_post_titles_list_txt);
        int[] numberFollowing = getResources().getIntArray(R.array.example_following_list_txt);
        int[] numberComments = getResources().getIntArray(R.array.example_comments_list_txt);
        for (int i = 0; i < userName.length; i++) { // arrays should be equal length
            postListModels.add(new PostListModel(userAva[i], userName[i], time[i], PostTitle[i],
                    locationTag[i], activityTag[i], postContent[i], numberFollowing[i], numberComments[i]));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PostListViewModel.class);
        // TODO: Use the ViewModel
    }

}