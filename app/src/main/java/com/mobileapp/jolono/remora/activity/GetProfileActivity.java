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
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.fragment.profile.ProfileFragment;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.model.Profile;
import com.mobileapp.jolono.remora.model.RequestManager;

import org.json.JSONObject;


public class GetProfileActivity extends ActionBarActivity  {
    private static final String FRAG_TAG = "frag";

    private void loadFragments() {
        Fragment frag;
        if((frag = getFragmentManager().findFragmentByTag(FRAG_TAG)) != null) {
            FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
            fragTrans.remove(frag);
            fragTrans.commit();
            getFragmentManager().executePendingTransactions();
        }

        //final String url = getIntent().getStringExtra("url");
        final String id = getIntent().getStringExtra("id");
        final String url = "http://ec2-52-0-168-55.compute-1.amazonaws.com/profiles/" + id + ".json";
        JsonObjectRequest request = Profile.getRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Profile profile = new Profile(response);
                profile.mObjUrl = url;
                Fragment profileFrag = ProfileFragment.newInstance(profile);
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                fragTrans.add(R.id.activity_view_profile_base, profileFrag, FRAG_TAG);
                fragTrans.commit();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });

        RequestManager.getInstance(this).addToRequestQueue(request);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_profile);
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
        getMenuInflater().inflate(R.menu.menu_view_profile, menu);
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
