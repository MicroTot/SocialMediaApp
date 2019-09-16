package com.demo.csc214.socialmediaapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.csc214.socialmediaapp.R;
import com.demo.csc214.socialmediaapp.controller.CheckLoginInputs;
import com.demo.csc214.socialmediaapp.model.Database.UserDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText mUsernameField;
    EditText mPasswordField;

    Button mLoginButton;

    public final String USERID_KEY = "USER_ID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameField = findViewById(R.id.username_login_edit_text);

        mPasswordField = findViewById(R.id.password_login_edit_text);

        mLoginButton = findViewById(R.id.login_button_login_activity);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CheckLoginInputs.validUserName(mUsernameField, UserDatabase.getInstance(getApplicationContext()))) {
                    Toast.makeText(getApplicationContext(), "USERNAME INVALID", Toast.LENGTH_SHORT).show();
                }  else if (!CheckLoginInputs.validAccount(mUsernameField, mPasswordField, UserDatabase.getInstance(getApplicationContext()))) {
                    Toast.makeText(getApplicationContext(), "USERNAME AND PASSWORD DO NOT MATCH", Toast.LENGTH_SHORT).show();
                } else {
                    int user_id = CheckLoginInputs.getUserID(mUsernameField, mPasswordField, UserDatabase.getInstance(getApplicationContext()));

                    Log.i("Get ID", Integer.toString(user_id));

                    Intent myIntent = new Intent(LoginActivity.this, SocialActivity.class);

                    myIntent.putExtra(USERID_KEY, user_id);

                    startActivity(myIntent);
                }
            }
        });



    }
}
