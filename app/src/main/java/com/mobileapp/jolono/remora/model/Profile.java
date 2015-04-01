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
    //entry names for values in db.
    private static final String FIRST_NAME_ARG = "first_name";
    private static final String MIDDLE_NAME_ARG = "middle_name";
    private static final String LAST_NAME_ARG = "last_name";
    private static final String BIRTHDATE_ARG = "age";
    private static final String GENDER_ARG = "gender";
    private static final String DESCRIPTION_ARG = "description";
    private static final String URL_ARG = "url";

    private JSONObject mData;
    private JSONObject mPushData = new JSONObject();

    public String getFirstName() {
        try {
            return mData.getString(FIRST_NAME_ARG);
        } catch (JSONException e) {
        }
        return null;
    }

    public void setFirstName(String name) {
        try {
            mPushData.put(FIRST_NAME_ARG, name);
        } catch (JSONException e) {
        }
    }

    public String getBirthdate() {
        return null;
    }

    public void setBirthdate(String date) {
        try {
            mPushData.put(BIRTHDATE_ARG, date);
        } catch (JSONException e) {
        }
    }

    public String getGender() {
        try {
            return mPushData.getString(GENDER_ARG);
        } catch (JSONException e) {
        }
        return  null;
    }

    public void setGender(String gender) {
        try {
            mPushData.put(GENDER_ARG, gender);
        } catch (JSONException e) {
        }
    }

    public String getDescription() {
        try {
            return mData.getString(DESCRIPTION_ARG);
        } catch (JSONException e) {
        }
        return null;
    }

    public void setDescription(String description) {
        try {
            mPushData.put(DESCRIPTION_ARG, description);
        } catch (JSONException e) {
        }
    }

    public int getAge() { return 0; }

    public String mUrl; //optional

    public Profile() {
    }

    public Profile(JSONObject jsonObject) {
        mData = jsonObject;
        try {
            mUrl = mData.getString(URL_ARG);
        } catch (JSONException e) {

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
        String url = "http://ec2-52-0-168-55.compute-1.amazonaws.com/profiles/2.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, mPushData, responseListener, errorListener);

        return  request;
    }

    /**
     * Creates a request to create a profile.
     * @param responseListener
     * @param errorListener
     * @return
     */
    public JsonObjectRequest createProfileRequest(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        String url = "http://ec2-52-0-168-55.compute-1.amazonaws.com/profiles.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, mPushData, responseListener, errorListener);

        return request;
    }

    @Override
    public String toString() {
        return getFirstName() + getBirthdate() + getGender();
    }
}
