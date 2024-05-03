package com.anu.gp24s1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anu.gp24s1.MainActivity;
import com.anu.gp24s1.R;
import com.anu.gp24s1.databinding.FragmentHomeBinding;
import com.anu.gp24s1.ui.following.FollowingModel;
import com.anu.gp24s1.ui.search.SearchFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        //list by locations
        RecyclerView recyclerViewLocation = binding.postListLocation;
        setUpRePostsByLocationModel();
        RePostsByLocationAdapter adapterLocation = new RePostsByLocationAdapter(getActivity(), rePostsByLocationModels);
        recyclerViewLocation.setAdapter(adapterLocation);
        recyclerViewLocation.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        //list by popular
        RecyclerView recyclerViewPopular = binding.postListPopular;
        setUpRePostsByPopularModel();
        RePostsByLocationAdapter adapterPopular = new RePostsByLocationAdapter(getActivity(), rePostsByLocationModels);
        recyclerViewPopular.setAdapter(adapterPopular);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        View root = binding.getRoot();
        return root;
    }

    ArrayList<RePostsByLocationModel> rePostsByLocationModels = new ArrayList<>();
    int[] userAva = {R.drawable.ic_outline_account_circle_24, R.drawable.ic_outline_account_circle_24, R.drawable.ic_outline_account_circle_24};

    //TODO: Set up group data
    // check this video: https://www.youtube.com/watch?v=Mc0XT58A1Z4
    private void setUpRePostsByLocationModel() {
        String[] userName = getResources().getStringArray(R.array.example_user_name_list_txt);
        String[] location = getResources().getStringArray(R.array.exapmle_location_list_txt);
        String[] activity = getResources().getStringArray(R.array.exapmle_activities_list_txt);
        String[] postTitle = getResources().getStringArray(R.array.exapmle_post_titles_list_txt);
        int[] numberOfFollowing = getResources().getIntArray(R.array.example_following_list_txt);
        int[] numberOfComments = getResources().getIntArray(R.array.example_comments_list_txt);
        for (int i = 0; i < userName.length; i++) { // arrays should be equal length
            rePostsByLocationModels.add(new RePostsByLocationModel(userAva[i], userName[i], location[i], activity[i],
                    postTitle[i], numberOfFollowing[i], numberOfComments[i]));
        }
    }

    private void setUpRePostsByPopularModel() {
        String[] userName = getResources().getStringArray(R.array.example_user_name_list_txt);
        String[] location = getResources().getStringArray(R.array.exapmle_location_list_txt);
        String[] activity = getResources().getStringArray(R.array.exapmle_activities_list_txt);
        String[] postTitle = getResources().getStringArray(R.array.exapmle_post_titles_list_txt);
        int[] numberOfFollowing = getResources().getIntArray(R.array.example_following_list_txt);
        int[] numberOfComments = getResources().getIntArray(R.array.example_comments_list_txt);
        for (int i = 0; i < userName.length; i++) { // arrays should be equal length
            rePostsByLocationModels.add(new RePostsByLocationModel(userAva[i], userName[i], location[i], activity[i],
                    postTitle[i], numberOfFollowing[i], numberOfComments[i]));
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup the button click listener, open search page when click to search bar
        binding.searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ensure the activity is correctly cast to MainActivity
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).replaceFragment(new SearchFragment());
                    ((MainActivity) getActivity()).binding.bottomNavigationBar.getMenu().getItem(1).setChecked(true);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}