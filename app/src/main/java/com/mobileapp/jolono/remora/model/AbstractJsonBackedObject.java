package com.mobileapp.jolono.remora.model;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Noah on 4/1/2015.
 */
public abstract class AbstractJsonBackedObject {
    protected JSONObject mData;
    protected JSONObject mPushData = new JSONObject();

    protected String mBaseUrl = "http://ec2-52-0-168-55.compute-1.amazonaws.com/";
    protected String mObjUrl;

    protected static final String URL_ARG = "url";

    public AbstractJsonBackedObject() {
    }

    public AbstractJsonBackedObject(JSONObject jsonObject) {
        mData = jsonObject;
        try {
            mObjUrl = mData.getString(URL_ARG);
        } catch (JSONException e) {
        }
    }

    public String getUrl() {
        return mObjUrl;
    }

    /**
     * Call getRequest to get a request to get a JsonObject backing this object.
     * Use response to construct this object.
     * @param url
     * @param responseListener
     * @param errorListener
     * @return
     */
    public static JsonObjectRequest getRequest(String url, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
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
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, mObjUrl, mPushData, responseListener, errorListener);

        return  request;
    }

    /**
     * Creates a request to create a profile.
     * @param responseListener
     * @param errorListener
     * @return
     */
    public JsonObjectRequest createProfileRequest(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, mBaseUrl, mPushData, responseListener, errorListener);

        return request;
    }
}
