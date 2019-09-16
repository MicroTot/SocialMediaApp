package com.demo.csc214.socialmediaapp.controller;

import android.widget.EditText;

import com.demo.csc214.socialmediaapp.model.Database.UserDatabase;
import com.demo.csc214.socialmediaapp.model.Entities.UserEntity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sailesh on 4/19/18.
 */

public class CheckCreateAccountInputs {

    //Source for checking validity of email: https://stackoverflow.com/questions/6119722/how-to-check-edittexts-text-is-email-address-or-not
    public static boolean checkValidEmail(EditText emailField) {
        String email = emailField.getText().toString();
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkEmailExists(EditText emailField, UserDatabase db) {
        if (db.userDAO().getAll().size() == 0) {
            return true;
        } else {
            List<UserEntity> list = db.userDAO().getAll();
            for (UserEntity user : list) {
                if (user.getEmail().equals(emailField.getText().toString())) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean checkValidUsername(EditText usernameField, UserDatabase db) {
        if (db.userDAO().getAll().size() == 0) {
            return true;
        } else {
            List<UserEntity> list = db.userDAO().getAll();
            for (UserEntity user : list) {
                if (user.getUsername().equals(usernameField.getText().toString())) {
                    return false;
                }
            }
            return true;
        }
    }

    public static String[] getValuesFromBoxes(EditText emailField, EditText usernameField, EditText passwordField) {
        String[] values = new String[3];

        values[0] = emailField.getText().toString();
        values[1] = usernameField.getText().toString();
        values[2] = passwordField.getText().toString();

        return values;
    }
}
