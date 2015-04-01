package com.mobileapp.jolono.remora.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.fragment.group.GroupFragment;
import com.mobileapp.jolono.remora.fragment.group.GroupHeaderFragment;
import com.mobileapp.jolono.remora.model.Group;
import com.mobileapp.jolono.remora.model.RequestManager;

import org.json.JSONArray;

/**
 * Created by Logan on 3/31/15.
 */
public class GetEventActivity extends Activity {
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

        Fragment headerFrag = GroupHeaderFragment.newInstance();
        FragmentTransaction f = getFragmentManager().beginTransaction();
        f.add(R.id.activity_get_group_base, headerFrag, FRAG_TAG_2);
        f.commit();

        String url = "http://dhh:secret@ec2-52-0-168-55.compute-1.amazonaws.com/demos.json";
        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Fragment groupFrag = GroupFragment.newInstance(new Group(response));
                        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                        fragTrans.add(R.id.activity_get_group_base, groupFrag, FRAG_TAG);
                        fragTrans.commit();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("blah", "blah");
            }
        });

        RequestManager.getInstance(this).addToRequestQueue(request);
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
