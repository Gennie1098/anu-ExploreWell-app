package com.anu.gp24s1.utils;

import com.anu.gp24s1.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseClient {
    private static FirebaseDatabaseClient instance;

    private final DatabaseReference mDatabase;

    private FirebaseDatabaseClient() {
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseDatabaseClient getInstance() {
        if (instance == null) {
            instance = new FirebaseDatabaseClient();
        }
        return instance;
    }

    public void writeUser(String userId, String email) {
        User user = new User(usernameFromEmail(email), email);

        mDatabase.child("users").child(userId).setValue(user);
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }
}
