package com.anu.gp24s1.utils;

import com.google.firebase.database.DatabaseReference;

public class DBConnector {

    private DBConnector instance;

    private DatabaseReference database;

    public DBConnector getInstance() {
        return instance;
    }
}
