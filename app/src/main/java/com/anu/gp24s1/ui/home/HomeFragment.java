package com.anu.gp24s1.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anu.gp24s1.MainActivity;
import com.anu.gp24s1.databinding.FragmentHomeBinding;
import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.state.UserSession;

import com.anu.gp24s1.ui.search.SearchFragment;
import com.anu.gp24s1.utils.DBConnector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Handler handler;
    private Runnable updateRunnable;
    RecyclerView recyclerViewLocation;
    RecyclerView recyclerViewPopular;
    Random random = new Random();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        //list by locations
        recyclerViewLocation = binding.postListLocation;
        setUpRePostsByLocationModel();
        RePostsByLocationAdapter adapterLocation = new RePostsByLocationAdapter(getActivity(), rePostsByLocationModels);
        recyclerViewLocation.setAdapter(adapterLocation);
        recyclerViewLocation.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        //list by popular
        recyclerViewPopular = binding.postListPopular;
        setUpRePostsByPopularModel();
        RePostsByLocationAdapter adapterPopular = new RePostsByLocationAdapter(getActivity(), rePostsByTagModels);
        recyclerViewPopular.setAdapter(adapterPopular);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        updateByTag();
        updateByLocation();

        View root = binding.getRoot();

        handler = new Handler(Looper.getMainLooper());
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                dataStream();
                handler.postDelayed(this, random.nextInt(6000) + 5000); // update interval: 5-10 second
            }
        };
        handler.post(updateRunnable);

        return root;
    }

    ArrayList<RePostsByLocationModel> rePostsByLocationModels = new ArrayList<>();

    /**
     * set recommendations by location to the list
     *
     * @author Qinjue Wu
     */
    private void setUpRePostsByLocationModel() {
        rePostsByLocationModels.clear();
        List<PostVo> recommendationByLocation = UserSession.getInstance().getRecommendationByLocation();
        if (recommendationByLocation == null || recommendationByLocation.size() == 0) {
            Toast.makeText(getContext(), "Get Recommended posts by location failed!", Toast.LENGTH_LONG).show();
        } else {
            for (int i = 0; i < recommendationByLocation.size(); i++) { // arrays should be equal length
                rePostsByLocationModels.add(new RePostsByLocationModel(recommendationByLocation.get(i).getAuthorAvatar(), recommendationByLocation.get(i).getAuthorName(), recommendationByLocation.get(i).getLocation(), recommendationByLocation.get(i).getTag(), recommendationByLocation.get(i).getTitle(), recommendationByLocation.get(i).getFollowerNumber(), recommendationByLocation.get(i).getCommentsNumber()));
            }
        }
    }

    ArrayList<RePostsByLocationModel> rePostsByTagModels = new ArrayList<>();

    /**
     * Set recommendations by tag to the list
     *
     * @author Qinjue Wu
     */
    private void setUpRePostsByPopularModel() {
        rePostsByTagModels.clear();
        List<PostVo> recommendationByTag = UserSession.getInstance().getRecommendationByTag();
        if (recommendationByTag == null || recommendationByTag.size() == 0) {
            Toast.makeText(getContext(), "Get Recommended posts by tag failed!", Toast.LENGTH_LONG).show();
        } else {
            for (int i = 0; i < recommendationByTag.size(); i++) { // arrays should be equal length
                rePostsByTagModels.add(new RePostsByLocationModel(recommendationByTag.get(i).getAuthorAvatar(), recommendationByTag.get(i).getAuthorName(), recommendationByTag.get(i).getLocation(), recommendationByTag.get(i).getTag(), recommendationByTag.get(i).getTitle(), recommendationByTag.get(i).getFollowerNumber(), recommendationByTag.get(i).getCommentsNumber()));
            }
        }
    }

    private void updateByTag() {
        DatabaseReference postDatabase = DBConnector.getInstance().getDatabase().child("tag");
        postDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setUpRePostsByPopularModel();
                recyclerViewPopular.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateByLocation() {
        DatabaseReference postDatabase = DBConnector.getInstance().getDatabase().child("location");
        postDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setUpRePostsByLocationModel();
                recyclerViewLocation.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

    public ArrayList<RePostsByLocationModel> getRePostsByLocationModels() {
        return rePostsByLocationModels;
    }

    private void dataStream() {
        int randomEventNumber = random.nextInt(10);
        switch (randomEventNumber) {
            case 0:
                if (rePostsByTagModels.size() > 0) {
                    // recommend posts by tag
                    RePostsByLocationModel rePostsByTagModel = rePostsByTagModels.get(0);
                    if (rePostsByTagModel.getNumberOfFollowing() < 27) {
                        rePostsByTagModel.setNumberOfFollowing(rePostsByTagModel.getNumberOfFollowing() + random.nextInt(3)); // randomly add 0 to 2
                        rePostsByTagModels.set(0, rePostsByTagModel);
                        recyclerViewPopular.getAdapter().notifyItemChanged(0);
                    }
                }
                break;
            case 1:
                if (rePostsByTagModels.size() > 0) {
                    // recommend posts by tag
                    RePostsByLocationModel rePostsByTagModel = rePostsByTagModels.get(1);
                    if (rePostsByTagModel.getNumberOfFollowing() < 27) {
                        rePostsByTagModel.setNumberOfFollowing(rePostsByTagModel.getNumberOfFollowing() + random.nextInt(3)); // randomly add 0 to 2
                        rePostsByTagModels.set(1, rePostsByTagModel);
                        recyclerViewPopular.getAdapter().notifyItemChanged(0);
                    }
                }
                break;
            case 2:
                if (rePostsByTagModels.size() > 0) {
                    // recommend posts by tag
                    RePostsByLocationModel rePostsByTagModel = rePostsByTagModels.get(2);
                    if (rePostsByTagModel.getNumberOfFollowing() < 27) {
                        rePostsByTagModel.setNumberOfFollowing(rePostsByTagModel.getNumberOfFollowing() + random.nextInt(3)); // randomly add 0 to 2
                        rePostsByTagModels.set(2, rePostsByTagModel);
                        recyclerViewPopular.getAdapter().notifyItemChanged(0);
                    }
                }
                break;
            case 3:
                if (rePostsByTagModels.size() > 0) {
                    // recommend posts by tag
                    RePostsByLocationModel rePostsByTagModel = rePostsByTagModels.get(3);
                    if (rePostsByTagModel.getNumberOfFollowing() < 27) {
                        rePostsByTagModel.setNumberOfFollowing(rePostsByTagModel.getNumberOfFollowing() + random.nextInt(3)); // randomly add 0 to 2
                        rePostsByTagModels.set(3, rePostsByTagModel);
                        recyclerViewPopular.getAdapter().notifyItemChanged(0);
                    }
                }
                break;
            case 4:
                if (rePostsByTagModels.size() > 0) {
                    // recommend posts by tag
                    RePostsByLocationModel rePostsByTagModel = rePostsByTagModels.get(4);
                    if (rePostsByTagModel.getNumberOfFollowing() < 27) {
                        rePostsByTagModel.setNumberOfFollowing(rePostsByTagModel.getNumberOfFollowing() + random.nextInt(3)); // randomly add 0 to 2
                        rePostsByTagModels.set(4, rePostsByTagModel);
                        recyclerViewPopular.getAdapter().notifyItemChanged(0);
                    }
                }
                break;
            case 6:
                if (getRePostsByLocationModels().size() > 0) {
                    // recommend posts by location
                    RePostsByLocationModel rePostsByLocationModel = rePostsByLocationModels.get(1);
                    if (rePostsByLocationModel.getNumberOfFollowing() < 27) {
                        rePostsByLocationModel.setNumberOfFollowing(rePostsByLocationModel.getNumberOfFollowing() + random.nextInt(3)); // randomly add 0 to 2
                        rePostsByLocationModels.set(1, rePostsByLocationModel);
                        recyclerViewLocation.getAdapter().notifyItemChanged(0);
                    }
                }
                break;
            case 7:
                if (getRePostsByLocationModels().size() > 0) {
                    // recommend posts by location
                    RePostsByLocationModel rePostsByLocationModel = rePostsByLocationModels.get(2);
                    if (rePostsByLocationModel.getNumberOfFollowing() < 27) {
                        rePostsByLocationModel.setNumberOfFollowing(rePostsByLocationModel.getNumberOfFollowing() + random.nextInt(3)); // randomly add 0 to 2
                        rePostsByLocationModels.set(2, rePostsByLocationModel);
                        recyclerViewLocation.getAdapter().notifyItemChanged(0);
                    }
                }
                break;
            case 8:
                if (getRePostsByLocationModels().size() > 0) {
                    // recommend posts by location
                    RePostsByLocationModel rePostsByLocationModel = rePostsByLocationModels.get(3);
                    if (rePostsByLocationModel.getNumberOfFollowing() < 27) {
                        rePostsByLocationModel.setNumberOfFollowing(rePostsByLocationModel.getNumberOfFollowing() + random.nextInt(3)); // randomly add 0 to 2
                        rePostsByLocationModels.set(3, rePostsByLocationModel);
                        recyclerViewLocation.getAdapter().notifyItemChanged(0);
                    }
                }
                break;
            case 9:
                if (getRePostsByLocationModels().size() > 0) {
                    // recommend posts by location
                    RePostsByLocationModel rePostsByLocationModel = rePostsByLocationModels.get(4);
                    if (rePostsByLocationModel.getNumberOfFollowing() < 27) {
                        rePostsByLocationModel.setNumberOfFollowing(rePostsByLocationModel.getNumberOfFollowing() + random.nextInt(3)); // randomly add 0 to 2
                        rePostsByLocationModels.set(4, rePostsByLocationModel);
                        recyclerViewLocation.getAdapter().notifyItemChanged(0);
                    }
                }
                break;
            default:
                if (getRePostsByLocationModels().size() > 0) {
                    // recommend posts by location
                    RePostsByLocationModel rePostsByLocationModel = rePostsByLocationModels.get(0);
                    if (rePostsByLocationModel.getNumberOfFollowing() < 27) {
                        rePostsByLocationModel.setNumberOfFollowing(rePostsByLocationModel.getNumberOfFollowing() + random.nextInt(3)); // randomly add 0 to 2
                        rePostsByLocationModels.set(0, rePostsByLocationModel);
                        recyclerViewLocation.getAdapter().notifyItemChanged(0);
                    }
                }
        }
    }
}