package com.mobileapp.jolono.remora.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Noah on 3/28/2015.
 */
public class RequestManager {
    private static RequestManager mInstance;

    private Context mContext;
    private RequestQueue mRequestQueue;

    private RequestManager(Context context) {
        mContext = context;
    }

    public static synchronized RequestManager getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new RequestManager(context);
        }

        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
