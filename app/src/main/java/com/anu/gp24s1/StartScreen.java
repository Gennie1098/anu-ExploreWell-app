package com.anu.gp24s1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anu.gp24s1.ui.login.LoginActivity;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Button loginbtn = findViewById(R.id.LogInButton);
        loginbtn.setOnClickListener(view -> {
            startActivity(new Intent(StartScreen.this, LoginActivity.class));
        });

        Button signupbtn = findViewById(R.id.SignUpButton);
//        signupbtn.setOnClickListener(view -> {
//            startActivity(new Intent(StartScreen.this, LoginActivity.class));
//        });
    }


    public void goToActivity2 (View view){
        Intent intent = new Intent (this, LoginActivity.class);
        startActivity(intent);
    }
}