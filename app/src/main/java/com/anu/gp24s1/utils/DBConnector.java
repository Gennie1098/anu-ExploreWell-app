package com.anu.gp24s1.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Connect to the firebase database, using the singleton design patter to ensure only one connection.
 */
public class DBConnector {

    private static DBConnector instance;

    private DatabaseReference database;

    private DBConnector(){
        database = FirebaseDatabase.getInstance().getReference();
    };

    /**
     * if haven't connect with the database, then build connection
     * @return instance
     * @author Qinjue Wu
     */
    public static DBConnector getInstance() {
        if(instance == null)
        {
            instance = new DBConnector();
        }
        return instance;
    }

    public DatabaseReference getDatabase() {
        return database;
    }
}
