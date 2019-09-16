package com.demo.csc214.socialmediaapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.csc214.socialmediaapp.R;
import com.demo.csc214.socialmediaapp.model.Database.FollowerDatabase;
import com.demo.csc214.socialmediaapp.model.Database.PostDatabase;
import com.demo.csc214.socialmediaapp.model.Database.ProfileDatabase;
import com.demo.csc214.socialmediaapp.model.Database.UserDatabase;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button mNewAccountButton;
    Button mLoginButton;

    UserDatabase userDB;
    ProfileDatabase profileDB;
    PostDatabase postDB;
    FollowerDatabase followDB;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth  = FirebaseAuth.getInstance();

        userDB = UserDatabase.getInstance(this);
        profileDB = ProfileDatabase.getInstance(this);
        postDB = PostDatabase.getInstance(this);
        followDB = FollowerDatabase.getInstance(this);

        mNewAccountButton = findViewById(R.id.new_account_button);

        mLoginButton = findViewById(R.id.existing_account_button);

        mNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });


    }
}
