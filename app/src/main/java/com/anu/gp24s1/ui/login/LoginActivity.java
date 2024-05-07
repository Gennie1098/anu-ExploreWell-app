package com.anu.gp24s1.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.anu.gp24s1.MainActivity;
import com.anu.gp24s1.R;
import com.anu.gp24s1.dao.CommentDaoImpl;
import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.dao.UserDaoImpl;
import com.anu.gp24s1.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        final EditText usernameEditText = binding.userEmail;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.LogInButton;
        final ProgressBar loadingProgressBar = binding.loading;


        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            loginButton.setEnabled(loginFormState.isDataValid());
            if (loginFormState.getUsernameError() != null) {
                usernameEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }
            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        };

        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);

        loginButton.setOnClickListener(v -> {
            loadingProgressBar.setVisibility(View.VISIBLE);
            loginViewModel.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
        });

        loginViewModel.getUserLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                Toast.makeText(getApplication(), "Logged In User: " + firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
            }
        });

        loginViewModel.getLoginResult().observe(this, loginResult -> {
            if (loginResult == null) {
                return;
            }
            loadingProgressBar.setVisibility(View.GONE);
            if (loginResult.isSuccess()) {
                updateUiWithUser(loginViewModel.getUserLiveData().getValue().getEmail());
                setResult(Activity.RESULT_OK);
                //Complete and destroy login activity once successful
                finish();
            } else {
                Toast.makeText(getApplication(), R.string.login_failed, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // TODO : initiate successful logged in experience
    private void updateUiWithUser(String username) {
        // Create an Intent to start MainActivity
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("USER_NAME", username);
        startActivity(intent);

        Toast.makeText(getApplication(), getString(R.string.welcome), Toast.LENGTH_LONG).show();
    }

}