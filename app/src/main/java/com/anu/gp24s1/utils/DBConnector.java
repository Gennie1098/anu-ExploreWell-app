package com.anu.gp24s1.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Connect to the firebase database, using the singleton design patter to ensure only one connection.
 */
public class DBConnector {

    private DBConnector instance;

    private DatabaseReference database;

    /**
     * if haven't connect with the database, then build connection
     * @return instance
     * @author Qinjue Wu
     */
    public DBConnector getInstance() {
        if(instance == null)
        {
            database = FirebaseDatabase.getInstance().getReference();
        }
        return instance;
    }

    public DatabaseReference getDatabase() {
        return database;
    }
}
