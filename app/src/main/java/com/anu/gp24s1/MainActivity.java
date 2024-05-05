package com.anu.gp24s1;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.anu.gp24s1.databinding.ActivityMainBinding;

import com.anu.gp24s1.ui.following.FollowingFragment;
import com.anu.gp24s1.ui.home.HomeFragment;
import com.anu.gp24s1.ui.profile.ProfileFragment;
import com.anu.gp24s1.ui.search.SearchFragment;

public class  MainActivity extends AppCompatActivity {

//    private FloatingActionButton fab;
//    private NavController navController;

    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationBar.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                replaceFragment(new HomeFragment());
                setTitle("Home");
            } else if (itemId == R.id.navigation_search) {
                replaceFragment(new SearchFragment());
            } else if (itemId == R.id.navigation_new_post) {
                Intent intent = new Intent(MainActivity.this, AddNewPostActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.navigation_following) {
                replaceFragment(new FollowingFragment());
            } else if (itemId == R.id.navigation_profile) {
                replaceFragment(new ProfileFragment());
            }
            return true;
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // Update the title of the activity
    public void updateTitle(String title) {
        setTitle(title);
    }



}
