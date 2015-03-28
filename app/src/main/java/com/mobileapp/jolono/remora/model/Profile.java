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

    private static final String NAME_ARG_NAME = "name";
    private static final String AGE_ARG_NAME = "age";


//
//    private Profile(int id, String name, int age, String gender, String description) {
//        mId = id;
//        mName = name;
//        mAge = age;
//        mGender = gender;
//        mDescription = description;
//    }

    public Profile(JSONObject jsonObject) {
        try {
            mName = jsonObject.getString(NAME_ARG_NAME);
        } catch (JSONException e) {
            Log.e(e.getClass().toString(), e.getMessage());
        }
    }


//    public static Profile getProfile(int id) {
////        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, s, null,
////                new Response.Listener<JSONObject>() {
////                    @Override
////                    public void onResponse(JSONObject response) {
////                        //Call a Static Factory Method (this is where we parse)
////                        return new Profile()
////                    }
////
////                }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
////
////            }
////        });
//
//        //if profile with id in cache, get profile from cache and return.
//        //else connect to database with separate thread, retrieve profile data, create new
//        //profile instance and return.
//        Profile profile = new Profile(id, "Noah Torrance", 23, "male", "I'm a pretty cool guy.");
//
//        return profile;
//    }



}
