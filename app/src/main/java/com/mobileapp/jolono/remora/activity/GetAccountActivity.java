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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.fragment.AccountCredentialsFragment;
import com.mobileapp.jolono.remora.fragment.profile.ProfileFragment;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.model.RequestManager;
import com.mobileapp.jolono.remora.model.UserAccount;

import org.json.JSONObject;


public class GetAccountActivity extends ActionBarActivity implements View.OnClickListener, AccountCredentialsFragment.OnFragmentInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_account);

        //final String url = UserAccount.getBaseURL() + "?username=" + UserAccount.mAccountName;
        final String userName = getIntent().getStringExtra("username");
        JsonObjectRequest request = UserAccount.getAccountRequest(userName, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                UserAccount.getAccountFromJSONObject(response);
                Fragment accountCredFrag = AccountCredentialsFragment.newInstance();
                Fragment accountProfileFrag = ProfileFragment.newInstance(UserAccount.mUserProfile);
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                fragTrans.add(R.id.activity_get_account_base, accountCredFrag);
                fragTrans.add(R.id.activity_get_account_base, accountProfileFrag);
                fragTrans.commit();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                // Log.e("fuck you", volleyError.getMessage());
            }
        });

        RequestManager.getInstance(this).addToRequestQueue(request);

        findViewById(R.id.activity_get_account_events_button).setOnClickListener(this);
        findViewById(R.id.activity_get_account_map_button).setOnClickListener(this);
        findViewById(R.id.activity_get_account_groups_button).setOnClickListener(this);
        findViewById(R.id.activity_get_account_search_button).setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case R.id.action_search:
                Log.d("menu", "search");
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //prob should just handle in fragment but wanted to show example of frag to activity interaction.
    @Override
    public void onChangePassword() {
        Log.d("fragTEst", "I just changed the password! (not really)");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.activity_get_account_events_button:
                startActivity(new Intent(this, GetEventsFromAccount.class));
                break;
            case R.id.activity_get_account_map_button:
                startActivity(new Intent(this, MapsActivity.class));
                break;
            case R.id.activity_get_account_groups_button:
                startActivity(new Intent(this, GetGroupsFromAccountActivity.class));
                break;
            case R.id.activity_get_account_search_button:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
    }
}


