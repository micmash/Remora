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
public class Profile extends AbstractJsonBackedObject {
    //entry names for values in db.
    private static final String FIRST_NAME_ARG = "first_name";
    private static final String MIDDLE_NAME_ARG = "middle_name";
    private static final String LAST_NAME_ARG = "last_name";
    private static final String BIRTHDATE_ARG = "age";
    private static final String GENDER_ARG = "gender";
    private static final String DESCRIPTION_ARG = "description";

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

    public Profile(String name) {
        super();
        mBaseUrl = "http://ec2-52-0-168-55.compute-1.amazonaws.com/profiles.json";
        setFirstName(name);
    }

    public Profile(JSONObject jsonObject) {
        super(jsonObject);
        mBaseUrl = "http://ec2-52-0-168-55.compute-1.amazonaws.com/profiles.json";
    }

    @Override
    public String toString() {
        return getFirstName() + getBirthdate() + getGender();
    }
}
