package com.mobileapp.jolono.remora.model;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.fragment.ProfileFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * TODO: Move to separate package for organization.
 * Profile is public information about a user.
 * UserAccount contains a profile but we don't want to pull account information if not user.
 *
 * Profile cannot directly update itself on database as the only person who can is the holder
 * of the account. i.e. account can be updated with new profile, profile cannot update itself.
 *
 * Created by Noah on 3/21/2015.
 */
public class Profile {
    public int mId;
    public String mName;
    public int mAge;
    public String mGender;
    public String mDescription;

    public String mUrl;

    private static final String NAME_ARG_NAME = "name";
    private static final String AGE_ARG_NAME = "age";
    private static final String URL_ARG_URL = "url";

    public Profile(JSONObject jsonObject) {
        try {
            mName = jsonObject.getString(NAME_ARG_NAME);
            mUrl = jsonObject.getString(URL_ARG_URL);
            Log.d("asfdjl", "dskal;fjd");
        } catch (JSONException e) {
            Log.e(e.getClass().toString(), e.getMessage());
        }
    }

    public JsonObjectRequest getJSONRequest(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put(NAME_ARG_NAME, mName);
        } catch(JSONException e) {

        }

        String url = "http://ec2-52-0-168-55.compute-1.amazonaws.com/demos/1";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonObj, responseListener, errorListener);

        return  request;
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
