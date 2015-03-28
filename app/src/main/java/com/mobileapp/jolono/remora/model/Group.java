package com.mobileapp.jolono.remora.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noah on 3/28/2015.
 */
public class Group {
    public int mId;
    public String mName;
    public List<Profile> mMembers = new ArrayList<>();

    private static final String NAME_ARG_NAME = "name";

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
}
