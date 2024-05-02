package com.anu.gp24s1.ui.following;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anu.gp24s1.MainActivity;

import com.anu.gp24s1.R;
import com.anu.gp24s1.databinding.FragmentFollowingBinding;

import java.util.ArrayList;

public class FollowingFragment extends Fragment {

    private FragmentFollowingBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FollowingViewModel dashboardViewModel =
                new ViewModelProvider(this).get(FollowingViewModel.class);

        binding = FragmentFollowingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.followingGroupsList;

        setUpFollowingModel();
        followingListAdapter adapter = new followingListAdapter(getActivity(), followingModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Adding Divider
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);

        return root;
    }

    ArrayList<FollowingModel> followingModels = new ArrayList<>();
    int[] groupIcon = {R.drawable.ic_activities, R.drawable.ic_location};

    // TODO: Set up group data
    //  check this video: https://www.youtube.com/watch?v=Mc0XT58A1Z4
    private void setUpFollowingModel() {
        String[] groupName = getResources().getStringArray(R.array.example_group_name_list_txt);
        for (int i = 0; i < groupName.length; i++) { // 2 arrays should be equal length
            followingModels.add(new FollowingModel(groupName[i], groupIcon[i]));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Update the title of the activity when the fragment is resumed
        ((MainActivity) requireActivity()).updateTitle("Following");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}