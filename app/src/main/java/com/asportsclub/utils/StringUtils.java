package com.asportsclub.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by Mobikasa on 7/25/2016.
 */
public class StringUtils {

    public static boolean isEmpty(String data) {

        if (TextUtils.isEmpty(data)) {
            return true;
        } else {
            if (data == null) return true;
            String inputData = data.trim();
            if (inputData.equalsIgnoreCase("null")) {
                return true;
            } else if (inputData.equalsIgnoreCase("\"\"")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmailValid(String email) {
        Pattern emailPattern = Pattern.compile("[a-z0-9A-Z._-]+@[a-z]+\\.+[a-z]+");

        Boolean validation = emailPattern.matcher(email).matches();
        if (email.contains(".@")) {
            validation = false;
        }
        if (email.contains(" ")) {
            validation = false;
        }

        return validation;
    }

    public static boolean isPasswordLengthValid(String text, int length) {
        boolean result = false;

        if (text != null && text.length() > 0) {
            if (text.length() >= length) {
                return true;
            }
        }

        return result;
    }

    public static String pad(int c) {
        if (c >= 10) return String.valueOf(c);
        else return "0" + String.valueOf(c);
    }

}
