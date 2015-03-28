package com.mobileapp.jolono.remora.model;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

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
        } catch (JSONException e) {
            Log.e(e.getClass().toString(), e.getMessage());
        }
    }

    @Override
    public String toString() {
        return mName + " " + mAge + " " + mGender;
    }

}
