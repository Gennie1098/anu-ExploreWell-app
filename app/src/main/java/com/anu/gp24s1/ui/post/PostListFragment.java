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
import android.widget.Toast;

import com.anu.gp24s1.R;
import com.anu.gp24s1.databinding.FragmentHomeBinding;
import com.anu.gp24s1.databinding.FragmentPostListBinding;
import com.anu.gp24s1.pojo.vo.PostVo;
import com.anu.gp24s1.state.UserSession;
import com.anu.gp24s1.ui.home.HomeViewModel;
import com.anu.gp24s1.ui.home.RePostsByLocationAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostListFragment extends Fragment {

//    private PostListViewModel mViewModel;

    private FragmentPostListBinding binding;

    public static PostListFragment newInstance() {
        return new PostListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPostListBinding.inflate(inflater, container, false);
        RecyclerView recyclerViewLocation = binding.postList;

        Bundle bundle = getArguments();
        if (bundle != null) {
            Serializable postSerializable = bundle.getSerializable("postListModels");
            try {
                setUpPostListModels((List<PostVo>) postSerializable);
            }catch (Exception e)
            {
                Toast.makeText(getContext(), "Get following posts failed", Toast.LENGTH_SHORT).show();
            }
        }

        PostListAdapter adapter = new PostListAdapter(getActivity(), postListModels);
        adapter.setOnItemClickListener(this::handleItemClick);
        recyclerViewLocation.setAdapter(adapter);
        recyclerViewLocation.setLayoutManager(new LinearLayoutManager(getActivity()));

        View root = binding.getRoot();
        return root;
    }

    private void handleItemClick(PostVo item) {
        Intent intent = new Intent(getActivity(), SinglePostActivity.class);
        intent.putExtra("post_details", item);
        startActivity(intent);
    }

    ArrayList<PostVo> postListModels = new ArrayList<>();
//    int[] userAva = {R.drawable.ic_outline_account_circle_24, R.drawable.ic_outline_account_circle_24, R.drawable.ic_outline_account_circle_24};

    private void setUpPostListModels(List<PostVo> postsVoList) {
        postListModels.clear();
        postListModels.addAll(postsVoList);
    }

}