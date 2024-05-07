package com.anu.gp24s1.ui.following;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anu.gp24s1.MainActivity;

import com.anu.gp24s1.R;
import com.anu.gp24s1.dao.PostDao;
import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.databinding.FragmentFollowingBinding;
import com.anu.gp24s1.pojo.Post;
import com.anu.gp24s1.state.UserSession;

import java.util.ArrayList;
import java.util.List;

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

        return root;
    }

    ArrayList<FollowingModel> followingModels = new ArrayList<>();
    int[] groupIcon = {R.drawable.ic_activities, R.drawable.ic_location};

    /**
     * Get the groups of the user's following posts, show the group name and corresponding icons.
     * @author Qinjue Wu
     */
    private void setUpFollowingModel() {
        List<String> groups = UserSession.getInstance().viewFollowingGroups();
        if(groups == null || groups.size() == 0) {
            Toast.makeText(getContext(),"No following posts!", Toast.LENGTH_LONG).show();
        }
        else {
            for (int i = 0; i < groups.size(); ++i) {
                String groupName = groups.get(i);
                PostDao postDao = PostDaoImpl.getInstance();
                if(postDao.getAllTags().contains(groupName)) {
                    followingModels.add(new FollowingModel(groups.get(i),groupIcon[0]));
                }
                else if(postDao.getAllLocations().contains(groupName)) {
                    followingModels.add(new FollowingModel(groups.get(i),groupIcon[1]));
                }
            }
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