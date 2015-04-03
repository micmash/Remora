package com.mobileapp.jolono.remora.model;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Noah on 3/28/2015.
 */
public class Group extends AbstractJsonBackedObject {
    private static final String NAME_ARG = "name";
    private static final String DESCRIPTION_ARG = "description";
    private static final String ID_ARG = "id";

    public List<Profile> mMembers = new ArrayList<>();

    public Group(JSONObject jsonObject) {
        super(jsonObject);
        mBaseUrl = "http://ec2-52-0-168-55.compute-1.amazonaws.com/groups.json";
    }

    public Group(JSONArray jsonArray) {
        mBaseUrl = "http://ec2-52-0-168-55.compute-1.amazonaws.com/groups.json";
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

    public String getDescription() {
        try {
            return mData.getString(DESCRIPTION_ARG);
        } catch (JSONException e) {
        }
        return null;
    }

    public int getID() {
        try {
            return mData.getInt(ID_ARG);
        } catch(JSONException e) {

        }

        return 0;
    }

    public static JsonArrayRequest getGroupMembers(String url, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {
        JsonArrayRequest request = new JsonArrayRequest(url, responseListener, errorListener);
        return request;
    }

    public JsonObjectRequest addMemberRequest(int memberId, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        String url = "http://ec2-52-0-168-55.compute-1.amazonaws.com/attach_profile_to_group?g_id=" + getID() + "&" + "p_id=" + memberId;
        JsonObjectRequest addMemberRequest = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, errorListener);

        return addMemberRequest;
    }

    @Override
    public String toString() {
        return getName();
    }

}
