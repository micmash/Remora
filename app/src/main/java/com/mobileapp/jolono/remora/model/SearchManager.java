package com.mobileapp.jolono.remora.model;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noah on 4/2/2015.
 */
public class SearchManager {
    public List<Event> mEventSearchResults = new ArrayList<>();
    public List<Group> mGroupSearchResults = new ArrayList<>();

    private static final String mBaseUrl = "http://ec2-52-0-168-55.compute-1.amazonaws.com/";

    private static SearchManager mInstance;

    private SearchManager() {
    }

    public static SearchManager getInstance() {
        if(mInstance == null) mInstance = new SearchManager();
        return mInstance;
    }

    public void appendEventSearchResults(JSONArray results) {
        //do json parsing stuff.
        //append search results. so don't clear.
        try {
            //String name = jsonObject.getString(NAME_ARG_NAME);
            for(int i = 0; i < results.length(); ++i) {
                JSONObject jsonObject = results.getJSONObject(i);
                Event event = new Event(jsonObject);
                mEventSearchResults.add(event);
            }
        } catch (JSONException e) {
            Log.e(e.getClass().toString(), e.getMessage());
        }
    }

    public void appendGroupSearchResults(JSONArray results) {
        try {
            //String name = jsonObject.getString(NAME_ARG_NAME);
            for(int i = 0; i < results.length(); ++i) {
                JSONObject jsonObject = results.getJSONObject(i);
                Group group = new Group(jsonObject);
                mGroupSearchResults.add(group);
            }
        } catch (JSONException e) {
            Log.e(e.getClass().toString(), e.getMessage());
        }
    }

    public JsonArrayRequest searchEventRequest(String name, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {
        String url = mBaseUrl + "search_events.json?name=";
        try {
            url += java.net.URLEncoder.encode(name, "UTF-8");
        } catch(UnsupportedEncodingException e) {
        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, responseListener, errorListener);
        return jsonArrayRequest;
    }

    public JsonArrayRequest searchGroupRequest(String name, Response.Listener<JSONArray> responseListener, Response.ErrorListener errorListener) {
        String url = mBaseUrl + "/search_groups.json?name=" + name;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, responseListener, errorListener);
        return jsonArrayRequest;
    }





}
