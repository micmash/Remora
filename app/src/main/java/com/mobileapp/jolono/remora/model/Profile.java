package com.mobileapp.jolono.remora.model;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Profile is public information about a user.
 * Created by Noah on 3/21/2015.
 */
public class Profile {
    public int mId;
    public String mName;
    public int mAge;
    public String mGender;
    public String mDescription;

    public String mUrl; //optional

    //entry names for values in db.
    private static final String NAME_ARG = "name";
    private static final String BIRTHDATE_ARG = "age";
    private static final String GENDER_ARG = "gender";
    private static final String DESCRIPTION_ARG = "description";
    private static final String URL_ARG = "url";

    public Profile() {
    }

    public Profile(JSONObject jsonObject) {
        try {
            mName = jsonObject.getString(NAME_ARG);
            if(jsonObject.has(URL_ARG)) mUrl = jsonObject.getString(URL_ARG);
        } catch (JSONException e) {
            Log.e(e.getClass().toString(), e.getStackTrace().toString());
        }
    }

    /**
     * Creates a request to retrieve a profile.
     * @param url
     * @param responseListener should construct a Profile here.
     * @param errorListener
     * @return
     */
    public static JsonObjectRequest getProfileRequest(String url, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, errorListener);
        return request;
    }


    /**
     * Creates a request to edit this profile.
     * @param responseListener
     * @param errorListener
     * @return
     */
    public JsonObjectRequest editProfileRequest(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(NAME_ARG, mName);
        } catch(JSONException e) {
        }

        String url = "http://ec2-52-0-168-55.compute-1.amazonaws.com/demos/1.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonObj, responseListener, errorListener);

        return  request;
    }

    /**
     * Creates a request to create a profile.
     * @param responseListener
     * @param errorListener
     * @return
     */
    public JsonObjectRequest createProfileRequest(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(NAME_ARG, mName);
        } catch(JSONException e) {
        }

        String url = "http://ec2-52-0-168-55.compute-1.amazonaws.com/demos.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObj, responseListener, errorListener);

        return request;
    }

    @Override
    public String toString() {
        return mName + " " + mAge + " " + mGender;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if ( !(obj instanceof Profile )) return false;
        return ((Profile)obj).mId == mId;
    }

}
