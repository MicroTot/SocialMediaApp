package com.demo.csc214.socialmediaapp.controller;

import android.widget.EditText;

import java.net.URL;

/**
 * Created by Sailesh on 4/20/18.
 */

public class CheckCreatePostInputs {

    public static boolean checkInput(EditText textField, EditText urlField) {
        if (textField.getText().toString().equals("") || urlField.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    //Source: https://www.geeksforgeeks.org/check-if-url-is-valid-or-not-in-java/
    public static boolean validURL(EditText urlField) {

        /* Try creating a valid URL */
            try {
                new URL(urlField.getText().toString()).toURI();
                return true;
            }

            // If there was an Exception
            // while creating URL object
            catch (Exception e) {
                return false;
            }

    }

}
