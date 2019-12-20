package com.example.smartparking.utils;

import android.util.Patterns;

import java.util.regex.Pattern;

public class CommonFunctions {

    public static boolean checkstring(String value){
        if (value!=null && !value.equalsIgnoreCase("")&& value.length()>0) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean checkmobile(String value){
        if (value!=null && !value.equalsIgnoreCase("")&& value.length()==10) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean checkpassword(String value){
        if (value!=null && !value.equalsIgnoreCase("")&& value.length()>=6) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean checkemail(String value){
        if (value!=null && !value.equalsIgnoreCase("")&& value.length()>0 && Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),value)) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean checkverification(String value){
        if (value!=null && !value.equalsIgnoreCase("")&& value.length()==4) {
            return true;
        }
        else {
            return false;
        }
    }
}
