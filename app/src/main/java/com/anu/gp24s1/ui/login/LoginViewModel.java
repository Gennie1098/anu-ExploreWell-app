package com.anu.gp24s1.ui.login;

import android.app.Application;
import android.util.Patterns;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anu.gp24s1.R;
import com.anu.gp24s1.data.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    private final AuthRepository authRepository;
    private final MutableLiveData<FirebaseUser> userLiveData;
    private final MutableLiveData<Boolean> loggedOutLiveData;
    private final MutableLiveData<LoginFormState> loginFormState;
    private final MutableLiveData<LoginResult> loginResult;

    public LoginViewModel(Application application) {
        super(application);

        this.authRepository = AuthRepository.getInstance();
        this.userLiveData = authRepository.getUserLiveData();
        this.loggedOutLiveData = authRepository.getLoggedOutLiveData();
        this.loginFormState = new MutableLiveData<>();
        this.loginResult = new MutableLiveData<>();
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        authRepository.login(username, password);

        FirebaseUser currentUser = userLiveData.getValue();
        if (currentUser != null) {
            loginResult.setValue(new LoginResult(currentUser.getDisplayName()));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void logout() {
        authRepository.logout();
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    public LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<Boolean> getIsLoggedOut() {
        return loggedOutLiveData;
    }
}