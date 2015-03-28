package com.mobileapp.jolono.remora.model;

import android.util.Log;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * TODO: Put under separate package.
 * TODO: Should be backing database handler classes to basically do everything in class for it.
 * Only one user account at one time.
 *
 * Created by Noah on 3/23/2015.
 */
public class UserAccount {
    public static int mUserId = -1;
    public static String mAccountName = null;
    public static Profile mUserProfile = null;
    

/*    public static boolean login(String accountName, String password) {
        //call database to login.
        //if not successful return false.
        //else
        mUserId = 0; //assign from account pulled from database.
        mAccountName = accountName;
        //prob should pull profile with account.
        mUserProfile = Profile.getSelectedProfile(mUserId);

        return true;
    }

    public static boolean logout() {
        //call database to logout.
        mUserId = -1;
        mAccountName = null;
        mUserProfile = null;

        return true;
    }

    public static boolean changePassword(String oldPassword, String newPassword) {
        //call database with mAccountName and oldPassword encrypted to verify.
        //change password on database.
        //if successful return true
        //else
        return false;
    }

    public static boolean verify() {
        //pull stored session info. verify. if things check out. return true.
        //else false
        return true;
    }*/
}
