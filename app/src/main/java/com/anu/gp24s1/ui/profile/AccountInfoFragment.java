package com.anu.gp24s1.ui.profile;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anu.gp24s1.MainActivity;
import com.anu.gp24s1.R;
import com.anu.gp24s1.databinding.FragmentAccountInfoBinding;
import com.anu.gp24s1.databinding.FragmentHomeBinding;
import com.anu.gp24s1.databinding.FragmentProfileBinding;
import com.anu.gp24s1.pojo.vo.UserVo;
import com.anu.gp24s1.state.UserSession;
import com.anu.gp24s1.ui.search.SearchFragment;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountInfoFragment newInstance(String param1, String param2) {
        AccountInfoFragment fragment = new AccountInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentAccountInfoBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel homeViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentAccountInfoBinding.inflate(inflater, container, false);
        UserSession userSession = UserSession.getInstance();
        UserVo userVo = userSession.getProfile();
        if (userVo != null) {
            String username = userVo.getUsername();
            String location = userVo.getLocation();
            String avatar = userVo.getAvatar();
            Uri imageUrl = Uri.parse(avatar);
            // bind user's information
            TextView usernameTextView = binding.name;
            usernameTextView.setText(username);
            TextView emailTextView = binding.email;
            emailTextView.setText(username);
            TextView locationTextView = binding.location;
            locationTextView.setText(location);
            ImageView avatarImageView = binding.userImage;
            Picasso.get().load(imageUrl).into(avatarImageView);
        }

        View root = binding.getRoot();
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup the button click listener, back to profile page
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ensure the activity is correctly cast to MainActivity
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).replaceFragment(new ProfileFragment());
                    ((MainActivity) getActivity()).binding.bottomNavigationBar.getMenu().getItem(4).setChecked(true);
                }
            }
        });
    }

}