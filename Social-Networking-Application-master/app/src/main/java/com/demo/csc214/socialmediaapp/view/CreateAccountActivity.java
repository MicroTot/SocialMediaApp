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
import com.demo.csc214.socialmediaapp.controller.CheckCreateAccountInputs;
import com.demo.csc214.socialmediaapp.controller.CheckCreateProfileElements;
import com.demo.csc214.socialmediaapp.controller.CheckLoginInputs;
import com.demo.csc214.socialmediaapp.model.Database.UserDatabase;
import com.demo.csc214.socialmediaapp.model.Entities.UserEntity;

import java.util.LinkedList;
import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {

    EditText mEmailField;
    EditText mUserNameField;
    EditText mPasswordField;

    UserDatabase userDatabase;

    Button mCreateAccount;

    public final String USERID_KEY = "USER_ID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        userDatabase = UserDatabase.getInstance(this);

        mEmailField = findViewById(R.id.email_create_edit_text);
        mUserNameField = findViewById(R.id.username_create_edit_text);
        mPasswordField = findViewById(R.id.password_create_edit_text);

        mCreateAccount = findViewById(R.id.new_button_create_activity);

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean validEmail = CheckCreateAccountInputs.checkValidEmail(mEmailField);

                boolean validUsername = CheckCreateAccountInputs.checkValidUsername(mUserNameField, userDatabase);

                boolean emailNotExists = CheckCreateAccountInputs.checkEmailExists(mEmailField, userDatabase);

                if (!validEmail) {
                    Toast.makeText(getApplicationContext(), "Invalid E-mail", Toast.LENGTH_SHORT).show();
                } else if (!emailNotExists) {
                    Toast.makeText(getApplicationContext(), "Email Already Registered!", Toast.LENGTH_SHORT).show();
                } else if (!validUsername) {
                    Toast.makeText(getApplicationContext(), "Username Already Exists!", Toast.LENGTH_SHORT).show();
                } else {
                    String[] values = CheckCreateAccountInputs.getValuesFromBoxes(mEmailField, mUserNameField, mPasswordField);

                    UserEntity userEntity = new UserEntity();

                    userEntity.setEmail(values[0]);
                    userEntity.setUsername(values[1]);
                    userEntity.setPassword(values[2]);

                    UserDatabase.getInstance(getApplicationContext()).userDAO().insertAll(userEntity);

                    List<Integer> list = new LinkedList<>();

                    for (UserEntity entity : UserDatabase.getInstance(getApplicationContext()).userDAO().getAll()) {
                        Log.i("Current Database", entity.getUser_id() + " " + entity.getEmail() + " " + entity.getUsername() + " " + entity.getPassword());
                        list.add(entity.getUser_id());
                    }


                    Intent intent = new Intent(CreateAccountActivity.this, ProfileCreationActivity.class);
                    intent.putExtra(USERID_KEY, list.get(list.size() - 1));

                    Log.i("Create Account ID: ", Integer.toString(userEntity.getUser_id()));

                    startActivity(intent);


                }

            }
        });
    }
}
