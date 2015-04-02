package com.mobileapp.jolono.remora.model;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
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
    public static Profile mUserProfile = null;
    public static String mAccountName = "AndrewMack"; //TODO: change to null once db is working.
    private static final String ACCOUNT_NAME_ARG = "username";

    private static final String mBaseURL = "http://ec2-52-0-168-55.compute-1.amazonaws.com/accounts";

    public static String getBaseURL() {
        return mBaseURL;
    }

    public static JsonObjectRequest getAccountRequest(String url, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, errorListener);
        return request;
    }

    public static JsonObjectRequest createAccountRequest(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JSONObject account =  new JSONObject();
        try {
            account.put(ACCOUNT_NAME_ARG, mAccountName);
        } catch (JSONException e) {
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, mBaseURL + ".json", account, responseListener, errorListener);

        return request;
    }


}
