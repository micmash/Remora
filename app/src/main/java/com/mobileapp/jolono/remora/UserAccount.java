package com.mobileapp.jolono.remora;

import android.util.Log;

import com.mobileapp.jolono.remora.model.AbstractDatabaseObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * TODO: Put under separate package.
 * TODO: Should be backing database handler classes to basically do everything in class for it.
 * Only one user account at one time.
 *
 * Created by Noah on 3/23/2015.
 */
public class UserAccount extends AbstractDatabaseObject {
    public static int mUserId = -1;
    public static String mAccountName = null;
    public static Profile mUserProfile = null;
    
    
    public UserAccount getObject(int id) {


        // Simulate network access.
        String s = super.baseDomain;
        URL url = null;
        try {
            url = new URL(s);
            //add stuff here 
            JSONObject json = super.getJsonResponse(url);
        } catch (MalformedURLException e) {
            Log.d("WOMP WOMP BAD URL", e.getMessage());
        }


           
           // Log.d("response", name);
            
        return this;
    }
    
    public boolean putObject() {
        
        
        return false;
    }


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
