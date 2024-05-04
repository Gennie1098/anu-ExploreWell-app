package com.anu.gp24s1.ui.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anu.gp24s1.R;
import com.anu.gp24s1.data.AuthRepository;
import com.anu.gp24s1.utils.Validator;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel {

    private final AuthRepository authRepository;
    private final MutableLiveData<FirebaseUser> userLiveData;
    private final MutableLiveData<LoginFormState> loginFormState;
    private final MutableLiveData<LoginResult> loginResult;

    public LoginViewModel(Application application) {
        super(application);

        this.authRepository = AuthRepository.getInstance();
        this.userLiveData = authRepository.getUserLiveData();
        this.loginResult = authRepository.getLoginResult();
        this.loginFormState = new MutableLiveData<>();
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        authRepository.login(username, password);
    }

    public void loginDataChanged(String username, String password) {
        if (!Validator.isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!Validator.isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
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

}