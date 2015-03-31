package com.mobileapp.jolono.remora.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.fragment.AccountCredentialsFragment;
import com.mobileapp.jolono.remora.fragment.ProfileFragment;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.model.Profile;
import com.mobileapp.jolono.remora.model.RequestManager;
import com.mobileapp.jolono.remora.model.UserAccount;

import org.json.JSONObject;


public class
        GetAccountActivity extends ActionBarActivity implements AccountCredentialsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_get_account);
//
//        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
//
//        Fragment accountCredFrag = AccountCredentialsFragment.newInstance(UserAccount.mAccountName);
//        fragTrans.add(R.id.activity_get_account_fragment_container, accountCredFrag);
//        fragTrans.commit();
//
//        String url = "http://dhh:secret@ec2-52-0-168-55.compute-1.amazonaws.com/accounts/3.json";
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Fragment profileFrag = ProfileFragment.newInstance(new Profile(response));
//                        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
//                        fragTrans.add(R.id.activity_view_profile_base, profileFrag);
//                        fragTrans.commit();
//                    }
//
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        RequestManager.getInstance(this).addToRequestQueue(request);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_account, menu);
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

    //prob should just handle in fragment but wanted to show example of frag to activity interaction.
    @Override
    public void onChangePassword() {
        Log.d("fragTEst", "I just changed the password! (not really)");
    }
}
