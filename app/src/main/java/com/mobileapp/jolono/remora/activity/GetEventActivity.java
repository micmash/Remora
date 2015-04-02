package com.mobileapp.jolono.remora.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.fragment.event.EventFragment;
import com.mobileapp.jolono.remora.fragment.event.EventHeaderFragment;
import com.mobileapp.jolono.remora.model.Event;
import com.mobileapp.jolono.remora.model.RequestManager;


import org.json.JSONArray;
import org.json.JSONObject;

public class GetEventActivity extends ActionBarActivity {
    private static final String FRAG_TAG = "h";
    private static final String FRAG_TAG_2 = "gl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_event);

        //remove fragments so new ones with updated info can be used.
        //TODO: this is kind of lame. prob should find a way to update frag withoout removing?
        Fragment frag;
        if((frag = getFragmentManager().findFragmentByTag(FRAG_TAG)) != null) {
            FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
            fragTrans.remove(frag);
            fragTrans.commit();
        }

        if((frag = getFragmentManager().findFragmentByTag(FRAG_TAG_2)) != null) {
            FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
            fragTrans.remove(frag);
            fragTrans.commit();
        }

        final String url1 = "http://dhh:secret@ec2-52-0-168-55.compute-1.amazonaws.com/events/1.json";
        JsonObjectRequest eventRequest = Event.getRequest(url1, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Fragment headerFrag = EventHeaderFragment.newInstance(new Event(response));
                FragmentTransaction f = getFragmentManager().beginTransaction();
                f.add(R.id.activity_get_event_header_fragment_container, headerFrag, FRAG_TAG);
                f.commit();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });

        final String url2 = "http://dhh:secret@ec2-52-0-168-55.compute-1.amazonaws.com/groups.json";
        JsonArrayRequest groupsRequest = Event.getEventGroups(url2, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Fragment eventFrag = EventFragment.newInstance(new Event(response));
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                fragTrans.add(R.id.activity_get_event_groups_fragment_container, eventFrag, FRAG_TAG_2);
                fragTrans.commit();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });

        RequestManager.getInstance(this).addToRequestQueue(eventRequest);
        RequestManager.getInstance(this).addToRequestQueue(groupsRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
