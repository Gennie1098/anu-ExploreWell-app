package com.anu.gp24s1;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.anu.gp24s1.databinding.ActivityMainBinding;

import com.anu.gp24s1.dao.CommentDao;
import com.anu.gp24s1.dao.CommentDaoImpl;
import com.anu.gp24s1.dao.PostDao;
import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.dao.UserDao;
import com.anu.gp24s1.dao.UserDaoImpl;
import com.anu.gp24s1.databinding.ActivityMainBinding;
import com.anu.gp24s1.ui.following.FollowingFragment;
import com.anu.gp24s1.ui.home.HomeFragment;
import com.anu.gp24s1.ui.profile.ProfileFragment;
import com.anu.gp24s1.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class  MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Start with HomeFragment()
        replaceFragment(new HomeFragment());

        // Update displayed Fragment based on the navigation click
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

    /**
     * Function to replace layout of Main activity (frameLayout) to the new fragment
     * for navigation interaction or page transition
     * fragment only, not available to transit to activity
     * @param fragment
     * author: Gennie
     */

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Update the title of the main activity
     * base on custom
     * @param title
     * author: Gennie
     */
    public void updateTitle(String title) {
        setTitle(title);
    }

}
