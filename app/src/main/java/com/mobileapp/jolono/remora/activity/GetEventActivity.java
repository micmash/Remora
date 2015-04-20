package com.mobileapp.jolono.remora.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.fragment.GroupListFragment;
import com.mobileapp.jolono.remora.fragment.event.EventHeaderFragment;
import com.mobileapp.jolono.remora.model.Event;
import com.mobileapp.jolono.remora.model.RequestManager;
import com.mobileapp.jolono.remora.model.UserAccount;


import org.json.JSONArray;
import org.json.JSONObject;

public class GetEventActivity extends ActionBarActivity implements View.OnClickListener {
    private static final String FRAG_TAG = "h";
    private static final String FRAG_TAG_2 = "gl";
    private static Button mdeleteButton;
    private final String url1 = "http://ec2-52-0-168-55.compute-1.amazonaws.com/events/";

    private Event mEvent;

    private void loadFragments() {
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

        final String id  = getIntent().getStringExtra("id");
        JsonObjectRequest eventRequest = Event.getRequest(url1 + id + ".json", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                mEvent= new Event(response);
                Fragment headerFrag = EventHeaderFragment.newInstance(new Event(response));
                FragmentTransaction f = getFragmentManager().beginTransaction();
                f.add(R.id.activity_get_event_header_fragment_container, headerFrag, FRAG_TAG);
                f.commit();

                if(mdeleteButton != null) {
                    if (mEvent.getOwnerUID().equals(UserAccount.mUID)) {
                        mdeleteButton.setOnClickListener(GetEventActivity.this);
                    } else {
                        mdeleteButton.setEnabled(false);
                    }
                }

                JsonArrayRequest groupsRequest = Event.getEventGroups(id, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        mEvent.getGroups(response);
                        Fragment eventFrag = GroupListFragment.newInstance(GetEventActivity.this, mEvent.mGroups);
                        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                        fragTrans.add(R.id.activity_get_event_groups_fragment_container, eventFrag, FRAG_TAG_2);
                        fragTrans.commit();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                });
                RequestManager.getInstance(GetEventActivity.this).addToRequestQueue(groupsRequest);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });

        RequestManager.getInstance(this).addToRequestQueue(eventRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_event);

        loadFragments();

        mdeleteButton = (Button) findViewById(R.id.activity_get_event_delete);
        if(mEvent != null) {
            if (mEvent.getOwnerUID().equals(UserAccount.mUID)) {
                mdeleteButton.setOnClickListener(this);
            } else {
                mdeleteButton.setEnabled(false);
            }
        }
    }

    @Override
    protected  void onRestart() {
        super.onRestart();
        loadFragments();
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

    
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.activity_get_event_delete:
                JsonObjectRequest delete = mEvent.deleteRequest(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("HAHAHASIJBAEC", volleyError.getMessage());
                    }
                }, (url1 + mEvent.getID()));
                RequestManager.getInstance(this).addToRequestQueue(delete);
                Intent i = new Intent(GetEventActivity.this, GetAccountActivity.class);
                i.putExtra("username", UserAccount.mAccountName);
                startActivity(i);
                finish();
                break;
        }
    }
}
