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
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.fragment.ProfileFragment;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.model.Profile;
import com.mobileapp.jolono.remora.model.RequestManager;

import org.json.JSONObject;


public class GetProfileActivity extends ActionBarActivity implements View.OnClickListener {
    private static final String FRAG_TAG = "frag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("life cycle", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_profile);

        Fragment frag;
        if((frag = getFragmentManager().findFragmentByTag(FRAG_TAG)) != null) {
            FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
            fragTrans.remove(frag);
            fragTrans.commit();
            getFragmentManager().executePendingTransactions();
        }

        String url = getIntent().getStringExtra("url");
        JsonObjectRequest request = Profile.getProfileRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Profile profile = new Profile(response);
                Fragment profileFrag = ProfileFragment.newInstance(profile, true);
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                fragTrans.add(R.id.activity_view_profile_base, profileFrag, FRAG_TAG);
                fragTrans.commit();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });

//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Profile profile = new Profile(response);
//                        profile.mUrl = getIntent().getStringExtra("url");
//                        Fragment profileFrag = ProfileFragment.newInstance(profile, true);
//                        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
//                        fragTrans.add(R.id.activity_view_profile_base, profileFrag, FRAG_TAG);
//                        fragTrans.commit();
//                    }
//
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
        RequestManager.getInstance(this).addToRequestQueue(request);



        findViewById(R.id.viewprofile_activity_group_button).setOnClickListener(this);
        findViewById(R.id.viewmap_activity_map_button).setOnClickListener(this);
    }

    /* life cycle logging --------------------------------------------------------------------*/

    @Override
    protected void onStart() {
        Log.d("life cycle", "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("life cycle", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("life cycle", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("life cycle", "onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("life cycle", "onRestart");
        super.onRestart();
    }

    @Override
    protected  void onDestroy() {
        Log.d("life cycle", "onDestroy");
        super.onDestroy();
    }
    /*---------------------------------------------------------------------------------------*/


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

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.viewprofile_activity_group_button:
                startActivity(new Intent(this, GetGroupActivity.class));
                break;
            case R.id.viewmap_activity_map_button:
                startActivity(new Intent(this, MapsActivity.class));
        }
    }

}
