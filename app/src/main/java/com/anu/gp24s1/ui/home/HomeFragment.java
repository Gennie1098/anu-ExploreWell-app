package com.anu.gp24s1.ui.home;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anu.gp24s1.MainActivity;
import com.anu.gp24s1.R;
import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.databinding.FragmentHomeBinding;
import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.state.UserSession;
import com.anu.gp24s1.ui.following.FollowingModel;
import com.anu.gp24s1.ui.search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

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
        RePostsByLocationAdapter adapterPopular = new RePostsByLocationAdapter(getActivity(), rePostsByTagModels);
        recyclerViewPopular.setAdapter(adapterPopular);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        View root = binding.getRoot();
        return root;
    }

    ArrayList<RePostsByLocationModel> rePostsByLocationModels = new ArrayList<>();

    private void setUpRePostsByLocationModel() {
        List<PostVo> recommendationByLocation = UserSession.getInstance().getRecommendationByLocation();
        if(recommendationByLocation == null || recommendationByLocation.size() == 0) {
            Toast.makeText(getContext(),"Get Recommended posts by location failed!", Toast.LENGTH_LONG).show();
        }
        else {
            for (int i = 0; i < recommendationByLocation.size(); i++) { // arrays should be equal length
                rePostsByLocationModels.add(new RePostsByLocationModel(recommendationByLocation.get(i).getAuthorAvatar(), recommendationByLocation.get(i).getAuthorName(),
                        recommendationByLocation.get(i).getLocation(), recommendationByLocation.get(i).getTag(),
                        recommendationByLocation.get(i).getTitle(), recommendationByLocation.get(i).getFollowerNumber(), recommendationByLocation.get(i).getCommentsNumber()));
            }
        }
    }

    ArrayList<RePostsByLocationModel> rePostsByTagModels = new ArrayList<>();
    private void setUpRePostsByPopularModel() {
        List<PostVo> recommendationByTag = UserSession.getInstance().getRecommendationByTag();
        if(recommendationByTag == null || recommendationByTag.size() == 0) {
            Toast.makeText(getContext(),"Get Recommended posts by tag failed!", Toast.LENGTH_LONG).show();
        }
        else {
            for (int i = 0; i < recommendationByTag.size(); i++) { // arrays should be equal length
                rePostsByTagModels.add(new RePostsByLocationModel(recommendationByTag.get(i).getAuthorAvatar(), recommendationByTag.get(i).getAuthorName(),
                        recommendationByTag.get(i).getLocation(), recommendationByTag.get(i).getTag(),
                        recommendationByTag.get(i).getTitle(), recommendationByTag.get(i).getFollowerNumber(), recommendationByTag.get(i).getCommentsNumber()));
            }
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