package com.demo.csc214.socialmediaapp.controller;

import android.widget.EditText;
import android.widget.ImageButton;

import com.demo.csc214.socialmediaapp.R;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sailesh on 4/19/18.
 */

public class CheckCreateProfileElements {

    public static Set<String> dates = new HashSet<>();

    public static boolean checkEmptyFields(EditText firstNameField, EditText lastNameField, EditText dobField, EditText hometownField, EditText bioField) {
        if (firstNameField.getText().toString().equals("") || lastNameField.getText().toString().equals("") || hometownField.getText().toString().equals("") || dobField.getText().toString().equals("") || bioField.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /*
    public static boolean checkImageChanged(ImageButton imageButton) {
        Integer resource = (Integer) imageButton.getTag();

        if (resource == R.drawable.standard_pic) {
            return false;
        } else {
            return true;
        }
    }
    */

    //Source: https://stackoverflow.com/questions/11480542/fastest-way-to-tell-if-a-string-is-a-valid-date
    public static void dateBuilder() {

            for (int year = 1900; year < 2050; year++) {
                for (int month = 1; month <= 12; month++) {
                    for (int day = 1; day <= daysInMonth(year, month); day++) {
                        StringBuilder date = new StringBuilder();
                        date.append(String.format("%02d", month));
                        date.append("/");
                        date.append(String.format("%02d", day));
                        date.append("/");
                        date.append(String.format("%04d", year));
                        dates.add(date.toString());
                    }
                }
            }

    }

    public static boolean checkDateValid(EditText dobField) {
        dateBuilder();
        return dates.contains(dobField.getText().toString());
    }


    public static int daysInMonth(int year, int month) {
        if (year % 4 == 0 && month == 2) {
            return 29;
        } else if (month == 2) {
            return 28;
        } else if (month == 9 || month == 4 || month == 6 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }

    public static String[] getValuesFromBoxes(EditText firstNameField, EditText lastNameField, EditText dobField, EditText hometownField,  EditText bioField) {
        String[] values = new String[5];

        values[0] = firstNameField.getText().toString();
        values[1] = lastNameField.getText().toString();
        values[2] = dobField.getText().toString();
        values[3] = hometownField.getText().toString();
        values[4] = bioField.getText().toString();


        return values;
    }

}
