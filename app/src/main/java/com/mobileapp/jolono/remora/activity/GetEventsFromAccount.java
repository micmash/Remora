package com.mobileapp.jolono.remora.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.fragment.GroupListFragment;
import com.mobileapp.jolono.remora.fragment.event.EventListFragment;
import com.mobileapp.jolono.remora.model.Profile;
import com.mobileapp.jolono.remora.model.RequestManager;
import com.mobileapp.jolono.remora.model.UserAccount;
import com.mobileapp.jolono.remora.view.Toaster;

import org.json.JSONArray;

public class GetEventsFromAccount extends ActionBarActivity {

    private String FRAG_TAG_2 = "fy";


    private void loadFragments() {
        Fragment frag;
        if((frag = getFragmentManager().findFragmentByTag(FRAG_TAG_2)) != null) {
            FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
            fragTrans.remove(frag);
            fragTrans.commit();
        }

        JsonArrayRequest groupsRequest = Profile.getEventsRequest(new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                UserAccount.mUserProfile.getJoinedEvents(response);
                Fragment eventsFrag = EventListFragment.newInstance(UserAccount.mUserProfile.mJoinedEvents);
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                fragTrans.add(R.id.activity_get_events_from_account_event_list, eventsFrag, FRAG_TAG_2);
                fragTrans.commit();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("KADHK", volleyError.getMessage());
                Toaster.popShortSimpleToast(getApplicationContext(), "Lost network connection.");
                finish();

            }
        });

        RequestManager.getInstance(this).addToRequestQueue(groupsRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_events_from_account);
        loadFragments();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadFragments();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_events_from_account, menu);
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
