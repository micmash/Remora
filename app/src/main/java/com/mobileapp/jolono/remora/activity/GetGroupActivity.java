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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.fragment.group.GroupFragment;
import com.mobileapp.jolono.remora.fragment.ProfileFragment;
import com.mobileapp.jolono.remora.fragment.group.GroupHeaderFragment;
import com.mobileapp.jolono.remora.model.Group;
import com.mobileapp.jolono.remora.model.Profile;
import com.mobileapp.jolono.remora.model.RequestManager;

import org.json.JSONArray;
import org.json.JSONObject;


public class GetGroupActivity extends ActionBarActivity {
    private static final String FRAG_TAG = "gf";
    private static final String FRAG_TAG_2 = "fg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("life cycle", "view group, onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_group);

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


        final String url1 = getIntent().getStringExtra("url");//"http://dhh:secret@ec2-52-0-168-55.compute-1.amazonaws.com/groups/1.json";
        JsonObjectRequest groupRequest = Group.getRequest(url1, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Group group = new Group(response);
                        group.mObjUrl = url1;
                        Fragment headerFrag = GroupHeaderFragment.newInstance(group);
                        FragmentTransaction f = getFragmentManager().beginTransaction();
                        f.add(R.id.activity_get_group_header_fragment_base, headerFrag, FRAG_TAG_2);
                        f.commit();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });


        String url2 = "http://dhh:secret@ec2-52-0-168-55.compute-1.amazonaws.com/profiles.json";
        JsonArrayRequest groupMemberRequest = Group.getGroupMembers(url2,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Fragment groupFrag = GroupFragment.newInstance(new Group(response));
                        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                        fragTrans.add(R.id.activity_get_group_list_fragment_base, groupFrag, FRAG_TAG);
                        fragTrans.commit();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("blah", "blah");
            }
        });

        RequestManager.getInstance(this).addToRequestQueue(groupRequest);
        RequestManager.getInstance(this).addToRequestQueue(groupMemberRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_group, menu);
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
