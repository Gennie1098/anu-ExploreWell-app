package com.anu.gp24s1.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.anu.gp24s1.MainActivity;
import com.anu.gp24s1.databinding.FragmentHomeBinding;
import com.anu.gp24s1.databinding.FragmentProfileBinding;
import com.anu.gp24s1.ui.home.HomeViewModel;
import com.anu.gp24s1.ui.search.SearchFragment;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel homeViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup the button click listener, open search page when click to search bar
        binding.accountInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ensure the activity is correctly cast to MainActivity
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).replaceFragment(new AccountInfoFragment());
                    ((MainActivity) getActivity()).binding.bottomNavigationBar.getMenu().getItem(4).setChecked(true);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Update the title of the activity when the fragment is resumed
        ((MainActivity) requireActivity()).updateTitle("Profile");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}