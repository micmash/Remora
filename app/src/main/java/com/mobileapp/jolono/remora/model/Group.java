package com.mobileapp.jolono.remora.model;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Noah on 3/28/2015.
 */
public class Group {
    private JSONObject mData;
    private JSONObject mPushData;

    private static final String NAME_ARG = "name";
    private static final String DESCRIPTION_ARG = "description";


    public List<Profile> mMembers = new ArrayList<>();

    public Group(JSONObject jsonObject) {
        mData = jsonObject;
    }

    public Group(JSONArray jsonArray) {
        //do json parsing stuff.
        try {
            //String name = jsonObject.getString(NAME_ARG_NAME);
            for(int i = 0; i < jsonArray.length(); ++i) {
                JSONObject member = jsonArray.getJSONObject(i);
                Profile profile = new Profile(member);
                mMembers.add(profile);
            }
        } catch (JSONException e) {
            Log.e(e.getClass().toString(), e.getMessage());
        }
    }

    public String getName() {
        try {
            return mData.getString(NAME_ARG);
        } catch(JSONException e) {
        }
        return null;
    }

    /**
     * Creates a request to retrieve a profile.
     * @param url
     * @param responseListener should construct a Profile here.
     * @param errorListener
     * @return
     */
    public static JsonObjectRequest getGroupRequest(String url, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, errorListener);
        return request;
    }

    public static JsonArrayRequest getGroupMembers(String url, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {
        JsonArrayRequest request = new JsonArrayRequest(url, responseListener, errorListener);
        return request;
    }

}
