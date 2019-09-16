package com.demo.csc214.socialmediaapp.controller;

import android.widget.EditText;

import com.demo.csc214.socialmediaapp.model.Database.UserDatabase;
import com.demo.csc214.socialmediaapp.model.Entities.UserEntity;

import java.util.List;

/**
 * Created by Sailesh on 4/19/18.
 */

public class CheckLoginInputs {

    public static boolean validUserName(EditText userNameField, UserDatabase db) {
        for (UserEntity user : db.userDAO().getAll()) {
            if (user.getUsername().equals(userNameField.getText().toString())) {
                return true;
            }
        }

        return false;
    }

    public static boolean validAccount(EditText usernameField, EditText passwordField, UserDatabase db) {
        for (UserEntity user : db.userDAO().getAll()) {
            if (user.getUsername().equals(usernameField.getText().toString()) && user.getPassword().equals(passwordField.getText().toString())) {
                return true;
            }
        }

        return false;
    }

    public static int getUserID(EditText usernameField, EditText passwordField, UserDatabase db) {
        for (UserEntity user : db.userDAO().getAll()) {
            if (user.getUsername().equals(usernameField.getText().toString()) && user.getPassword().equals(passwordField.getText().toString())) {
                return user.getUser_id();
            }
        }
        return 10000;
    }
}
