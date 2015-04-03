package com.mobileapp.jolono.remora.model;

import android.provider.CalendarContract;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Profile is public information about a user.
 * Created by Noah on 3/21/2015.
 */
public class Profile extends AbstractJsonBackedObject {
    //entry names for values in db.
    private static final String FIRST_NAME_ARG = "fname";
    private static final String LAST_NAME_ARG = "lname";
    private static final String BIRTHDATE_ARG = "birthday";
    private static final String GENDER_ARG = "gender";
    private static final String DESCRIPTION_ARG = "description";
    private static final String UID_ARG = "u_id";
    public List<Group> mJoinedGroups = new ArrayList<Group>();
    public List<Event> mLoinedEvents;

    private static final String ID_ARG = "id";

    public String getFirstName() {
        try {
            return mData.getString(FIRST_NAME_ARG);
        } catch (JSONException e) {
        }
        return null;
    }

    public void setFirstName(String name) {
        try {
            mPushData.put(FIRST_NAME_ARG, name);
        } catch (JSONException e) {
        }
    }

    public String getLastName() {
        try {
            return mData.getString(LAST_NAME_ARG);
        } catch (JSONException e) {
        }
        return null;
    }

    public void setLastName(String name) {
        try {
            mPushData.put(LAST_NAME_ARG, name);
        } catch (JSONException e) {
        }
    }

    public String getBirthdate() {
        try {
            mData.getString(BIRTHDATE_ARG);
        } catch (JSONException e) {

        }
        return null;
    }

    public void setBirthdate(String date) {
        try {
            mPushData.put(BIRTHDATE_ARG, date);
        } catch (JSONException e) {
        }
    }

    public String getGender() {
        try {
            return mData.getString(GENDER_ARG);
        } catch (JSONException e) {
        }
        return  null;
    }

    public void setGender(String gender) {
        try {
            mPushData.put(GENDER_ARG, gender);
        } catch (JSONException e) {
        }
    }

    public String getDescription() {
        try {
            return mData.getString(DESCRIPTION_ARG);
        } catch (JSONException e) {
        }
        return null;
    }

    public void setDescription(String description) {
        try {
            mPushData.put(DESCRIPTION_ARG, description);
        } catch (JSONException e) {
        }
    }

    public UUID getUID() {
        try {
            return UUID.fromString(mData.getString(UID_ARG));
        } catch (JSONException e) {
        }
        return null;
    }

    public void setUID(UUID uid) {
        try {
            mPushData.put(UID_ARG, uid.toString());
        } catch (JSONException e) {
        }
    }

    public int getID() {
        try {
            return mData.getInt(ID_ARG);
        } catch (JSONException e) {
        }
        return 0;
    }

    public int getAge() { return 0; }

    public Profile(String fname, String lname, String birthdate, String gender, UUID uid) {
        super();
        mBaseUrl = "http://ec2-52-0-168-55.compute-1.amazonaws.com/profiles.json";
        setFirstName(fname);
        setLastName(lname);
        setBirthdate(birthdate);
        setGender(gender);
        setUID(uid);
    }

    public Profile(JSONObject jsonObject) {
        super(jsonObject);
        mBaseUrl = "http://ec2-52-0-168-55.compute-1.amazonaws.com/profiles.json";
        mObjUrl = "http://ec2-52-0-168-55.compute-1.amazonaws.com/profiles/" + getID() + ".json";
    }

    public static JsonArrayRequest getGroupsRequest(Response.Listener<JSONArray> response,
                                                    Response.ErrorListener listener) {
        String url = "http://ec2-52-0-168-55.compute-1.amazonaws.com" +
                "/get_attached_groups_to_profile.json?u_id=" + UserAccount.mUID.toString();
        JsonArrayRequest request = new JsonArrayRequest(url, response, listener);
        return request;
    }

    public void getJoinedGroups(JSONArray a) {
        try {
            for(int i = 0; i < a.length(); i++) {
                JSONObject g = a.getJSONObject(i);
                Group group = new Group((g));
                mJoinedGroups.add(group);

            }
        } catch (Exception e) {
            Log.d("e", e.getMessage());
        }

    }



    @Override
    public String toString() {
        return getFirstName() + getBirthdate() + getGender();
    }
}
