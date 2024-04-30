package com.anu.gp24s1;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class  MainActivity extends AppCompatActivity {

//    private FloatingActionButton fab;
//    private NavController navController;

    ActivityMainBinding binding;

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
            } else if (itemId == R.id.navigation_search) {

            } else if (itemId == R.id.navigation_following) {
                replaceFragment(new FollowingFragment());
            } else if (itemId == R.id.navigation_profile) {
                replaceFragment(new ProfileFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

//        setSupportActionBar(binding.toolbar);
//
//        fab = binding.fab;
//
//        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        navController.setGraph(R.navigation.nav_graph_java);
//        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
//            if (destination.getId() == R.id.MainFragment) {
//                fab.setVisibility(View.VISIBLE);
//            } else {
//                fab.setVisibility(View.GONE);
//            }
//        });
//    }


}
