package com.anu.gp24s1;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.anu.gp24s1.databinding.FragmentSignInBinding;
import com.anu.gp24s1.utils.FirebaseDatabaseClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "SignInFragment";

    private FirebaseDatabaseClient mDatabase;
    private FirebaseAuth mAuth;

    private FragmentSignInBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDatabase = FirebaseDatabaseClient.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Views
        setProgressBar(R.id.progressBar);

        // Click listeners
        binding.buttonSignIn.setOnClickListener(this);
//        binding.buttonSignUp.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check auth on Activity start
        if (mAuth.getCurrentUser() != null) {
            NavHostFragment.findNavController(this).navigate(R.id.action_SignInFragment_to_MainFragment);
        }
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.buttonSignIn) {
            signIn();
//        } else if (i == R.id.buttonSignUp) {
//            signUp();
        }
    }

    private void signIn() {
        Log.d(TAG, "signIn");
        if (!validateForm()) {
            return;
        }

        showProgressBar();
        String email = binding.fieldEmail.getText().toString();
        String password = binding.fieldPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(getActivity(), task -> {
                Log.d(TAG, "signIn:onComplete:" + task.isSuccessful());
                hideProgressBar();

                if (task.isSuccessful()) {
                    NavHostFragment.findNavController(this)
                        .navigate(R.id.action_SignInFragment_to_MainFragment);
                } else {
                    Toast.makeText(getContext(), "Sign In Failed", Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void signUp() {
        Log.d(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        showProgressBar();
        String email = binding.fieldEmail.getText().toString();
        String password = binding.fieldPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(getActivity(), task -> {
                Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                hideProgressBar();

                if (task.isSuccessful()) {
                    createUser(task.getResult().getUser());
                } else {
                    Toast.makeText(getContext(), "Sign Up Failed", Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void createUser(FirebaseUser user) {
        mDatabase.writeUser(user.getUid(), user.getEmail());

        NavHostFragment.findNavController(this).navigate(R.id.action_SignInFragment_to_MainFragment);
    }

    private boolean validateForm() {
        boolean result = true;

        if (TextUtils.isEmpty(binding.fieldEmail.getText().toString())) {
            binding.fieldEmail.setError("Email is required");
            result = false;
        } else {
            binding.fieldEmail.setError(null);
        }

        String password = binding.fieldPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            binding.fieldPassword.setError("Password is required");
            result = false;
        } else if (password.length() < 6) {
            binding.fieldPassword.setError("Password must be at least 6 characters");
            result = false;
        } else {
            binding.fieldPassword.setError(null);
        }

        return result;
    }
}
