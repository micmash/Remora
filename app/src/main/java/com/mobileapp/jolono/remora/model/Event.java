package com.mobileapp.jolono.remora.model;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noah on 4/1/2015.
 */
public class Event extends AbstractJsonBackedObject {
    private static final String NAME_ARG = "name";
    private static final String DESCRIPTION_ARG = "description";
    private static final String LOCATION_ARG = "location";
    private static final String START_TIME_ARG = "starttime";
    private static final String END_TIME_ARG = "endtime";

    public List<Group> mGroups = new ArrayList<>();

    public Event(JSONObject jsonObject) {
        super(jsonObject);
        mBaseUrl = "http://ec2-52-0-168-55.compute-1.amazonaws.com/events.json";
    }

    public Event(JSONArray jsonArray) {
        mBaseUrl = "http://ec2-52-0-168-55.compute-1.amazonaws.com/events.json";
        //do json parsing stuff.
        try {
            //String name = jsonObject.getString(NAME_ARG_NAME);
            for(int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Group group = new Group(jsonObject);
                mGroups.add(group);
            }
        } catch (JSONException e) {
            Log.e(e.getClass().toString(), e.getMessage());
        }
    }

    /* getter setters */
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

    public String setDescription(String description) {
        try {
            mPushData.put(DESCRIPTION_ARG, description);
        } catch (JSONException e) {
        }
        return null;
    }

    public String getLocation() {
        try {
            return mData.getString(LOCATION_ARG);
        } catch (JSONException e) {
        }
        return null;
    }

    public String setLocation(String location) {
        try {
            mPushData.put(LOCATION_ARG, location);
        } catch (JSONException e) {
        }
        return null;
    }

    public String getStartTime() {
        try {
            return mData.getString(START_TIME_ARG);
        } catch (JSONException e) {
        }
        return null;
    }

    public String setStartTime(String startTime) {
        try {
            mPushData.put(START_TIME_ARG, startTime);
        } catch (JSONException e) {
        }
        return null;
    }

    public String getEndTime() {
        try {
            return mData.getString(END_TIME_ARG);
        } catch (JSONException e) {
        }
        return null;
    }

    public String setEndTime(String endTime) {
        try {
            mPushData.put(END_TIME_ARG, endTime);
        } catch (JSONException e) {
        }
        return null;
    }

    public void getGroups(JSONArray jsonArray) {
        try {
            for(int i = 0; i < jsonArray.length(); ++i) {
                JSONObject group = jsonArray.getJSONObject(i);
                mGroups.add(new Group(group));
            }
        } catch (JSONException e) {
        }
    }

    public static JsonArrayRequest getEventGroups(String url, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {
        JsonArrayRequest request = new JsonArrayRequest(url, responseListener, errorListener);
        return request;
    }
}