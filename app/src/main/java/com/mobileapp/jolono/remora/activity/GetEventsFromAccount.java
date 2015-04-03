package com.mobileapp.jolono.remora.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.fragment.GroupListFragment;
import com.mobileapp.jolono.remora.model.Profile;
import com.mobileapp.jolono.remora.model.RequestManager;
import com.mobileapp.jolono.remora.model.UserAccount;

import org.json.JSONArray;

public class GetEventsFromAccount extends ActionBarActivity {

    private String FRAG_TAG_2 = "fy";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_events_from_account);
        JsonArrayRequest groupsRequest = Profile.getGroupsRequest(new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                UserAccount.mUserProfile.getJoinedGroups(response);
                Fragment groupsFrag = GroupListFragment.newInstance(UserAccount.mUserProfile.mJoinedGroups);
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                fragTrans.add(R.id.activity_get_groups_from_account_frag_container, groupsFrag, FRAG_TAG_2);
                fragTrans.commit();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });

        RequestManager.getInstance(this).addToRequestQueue(groupsRequest);
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
