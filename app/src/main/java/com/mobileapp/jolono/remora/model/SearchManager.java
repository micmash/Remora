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

    public void getEventSearchResults(JSONArray results) {
        //do json parsing stuff.
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

    public JsonArrayRequest searchEventRequest(Response.Listener<JsonArrayRequest> responseListener, Response.ErrorListener errorListener) {
        String url = mBaseUrl + "";
        //JsonArrayRequest jsonArrayRequest = new JsonArrayRequest()
        return null;
    }



}
