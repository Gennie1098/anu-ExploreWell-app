package com.anu.gp24s1.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import com.anu.gp24s1.MainActivity;
import com.anu.gp24s1.StartScreen;
import com.anu.gp24s1.data.AuthRepository;
import com.anu.gp24s1.databinding.FragmentProfileBinding;
import com.anu.gp24s1.pojo.vo.UserVo;
import com.anu.gp24s1.state.UserSession;
import com.anu.gp24s1.state.LogoutSession;

import com.squareup.picasso.Picasso;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel homeViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        // get user's information
        UserSession userSession = UserSession.getInstance();
        UserVo userVo = userSession.getProfile();
        if (userVo != null) {
            String username = userVo.getUsername();
            String avatar = userVo.getAvatar();
            Uri imageUrl = Uri.parse(avatar);
            // bind user's information
            TextView usernameTextView = binding.username;
            usernameTextView.setText(username);
            ImageView avatarImageView = binding.userImage;
            Picasso.get().load(imageUrl).into(avatarImageView);
        }
        
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup the button click listener, open search page when click to search bar
        binding.accountInfo.setOnClickListener(v -> {
            // Ensure the activity is correctly cast to MainActivity
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).replaceFragment(new AccountInfoFragment());
                ((MainActivity) getActivity()).binding.bottomNavigationBar.getMenu().getItem(4).setChecked(true);
            }
        });

        binding.logOut.setOnClickListener(v -> {
            UserSession userSession = UserSession.getInstance();
            userSession.changeState(new LogoutSession(userSession));
            userSession.setUserKey(null);
            AuthRepository.getInstance().logout();
            Toast.makeText(getContext(), "User Logged Out", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), StartScreen.class);
            startActivity(intent);
            requireActivity().finish();
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