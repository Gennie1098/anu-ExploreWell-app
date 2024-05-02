package com.anu.gp24s1.data;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class AuthRepository {

    private static AuthRepository instance;

    private final FirebaseAuth firebaseAuth;
    private final MutableLiveData<FirebaseUser> userLiveData;
    private final MutableLiveData<Boolean> loggedOutLiveData;

    private AuthRepository() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.userLiveData = new MutableLiveData<>();
        this.loggedOutLiveData = new MutableLiveData<>();

        if (firebaseAuth.getCurrentUser() != null) {
            firebaseAuth.signOut();
//            userLiveData.postValue(firebaseAuth.getCurrentUser());
//            loggedOutLiveData.postValue(false);
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
                    loggedOutLiveData.postValue(false);
                } else {
                    System.out.println("Registration Failure: " + task.getException().getMessage());
                }
            });
    }

    public void login(String username, String password) {
        firebaseAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    userLiveData.postValue(firebaseAuth.getCurrentUser());
                    loggedOutLiveData.postValue(false);
                } else {
                    System.out.println("Login Failure: " + task.getException().getMessage());
                }
            });
    }

    public void logout() {
        firebaseAuth.signOut();
        loggedOutLiveData.postValue(true);
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutLiveData() {
        return loggedOutLiveData;
    }
}