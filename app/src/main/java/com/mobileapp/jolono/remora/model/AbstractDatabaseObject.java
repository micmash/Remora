package com.mobileapp.jolono.remora.model;

import android.util.Log;

import com.android.volley.*;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


/**
 * Created by Logan on 3/27/15.
 */
public abstract class AbstractDatabaseObject {

    
    /**
     * Database Object ID
     */
    public int id = -1;
    public String baseDomain = "ec2-52-0-168-55.compute-1.amazonaws.com/";

    public abstract AbstractDatabaseObject getObject(int id);
    
    public JSONObject getJsonResponse(URL url) {
        JSONObject jsonObject = null;
        InputStream inputStream = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
    

            String response = "";
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()) {
                response = scanner.next();
            }
            jsonObject = (JSONObject) new JSONTokener(response).nextValue();
        } catch (Exception e) {
            Log.d("FUCK", e.getStackTrace().toString());
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (Exception e) {
                Log.d("KILL..... ME.....", e.getMessage());
            }
        }

        return jsonObject;


    }
    
    public abstract boolean putObject();
    
    

    
}
