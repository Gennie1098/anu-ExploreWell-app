package com.anu.gp24s1.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.anu.gp24s1.dao.CommentDaoImpl;
import com.anu.gp24s1.dao.PostDaoImpl;
import com.anu.gp24s1.dao.UserDaoImpl;
import com.anu.gp24s1.state.LoginSession;
import com.anu.gp24s1.state.UserSession;
import com.anu.gp24s1.ui.login.LoginResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class AuthRepository {

    private static final String TAG = "AuthRepository";

    private static AuthRepository instance;

    private final FirebaseAuth firebaseAuth;
    private final MutableLiveData<FirebaseUser> userLiveData;
    private final MutableLiveData<LoginResult> loginResult;

    private AuthRepository() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.userLiveData = new MutableLiveData<>();
        this.loginResult = new MutableLiveData<>();

        if (firebaseAuth.getCurrentUser() != null) {
            firebaseAuth.signOut();
            userLiveData.postValue(null);
        }
    }

    public static AuthRepository getInstance() {
        if (instance == null) {
            instance = new AuthRepository();
        }
        return instance;
    }

    public void register(String username, String password) {
        firebaseAuth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    userLiveData.postValue(firebaseAuth.getCurrentUser());
                    loginResult.postValue(new LoginResult(LoginResult.Status.SUCCESS));
                } else {
                    loginResult.postValue(new LoginResult(LoginResult.Status.FAIL));
                    Log.e(TAG, "Registration Failure: " + task.getException().getMessage());
                }
            });
    }

    public void login(String username, String password) {
        firebaseAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    userLiveData.postValue(firebaseAuth.getCurrentUser());
                    loginResult.postValue(new LoginResult(LoginResult.Status.SUCCESS));
                    UserSession userSession = UserSession.getInstance();
                    userSession.setUserKey(firebaseAuth.getCurrentUser().getUid());
                    userSession.changeState(new LoginSession(userSession));
                } else {
                    loginResult.postValue(new LoginResult(LoginResult.Status.FAIL));
                    Log.e(TAG, "Login Failure: " + task.getException().getMessage());
                }
            });
    }

    public void logout() {
        firebaseAuth.signOut();
        userLiveData.postValue(null);
        loginResult.setValue(null);
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

}