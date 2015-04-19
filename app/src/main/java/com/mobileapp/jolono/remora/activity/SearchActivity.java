package com.mobileapp.jolono.remora.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.fragment.event.EventListFragment;
import com.mobileapp.jolono.remora.model.RequestManager;
import com.mobileapp.jolono.remora.model.SearchManager;

import org.json.JSONArray;

public class SearchActivity extends ActionBarActivity {
    private static final String FRAG_TAG = "el";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //handleIntent(getIntent());

        Fragment frag;
        if((frag = getFragmentManager().findFragmentByTag(FRAG_TAG)) != null) {
            FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
            fragTrans.remove(frag);
            fragTrans.commit();
            getFragmentManager().executePendingTransactions();
        }

        findViewById(R.id.activity_search_search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = ((EditText) findViewById(R.id.activity_search_search)).getText().toString();
                JsonArrayRequest searchRequest = SearchManager.getInstance().searchEventRequest(search, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        SearchManager.getInstance().appendEventSearchResults(jsonArray);
                        Fragment eventListFrag = EventListFragment.newInstance(SearchManager.getInstance().mEventSearchResults);
                        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                        fragTrans.add(R.id.activity_search_base, eventListFrag, FRAG_TAG);
                        fragTrans.commit();
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                });
                RequestManager.getInstance(SearchActivity.this).addToRequestQueue(searchRequest);
            }
        });
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleIntent(Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_SEARCH)) {
            String query = intent.getStringExtra(android.app.SearchManager.QUERY);
            JsonArrayRequest searchRequest = SearchManager.getInstance().searchEventRequest(query, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonArray) {
                    SearchManager.getInstance().appendEventSearchResults(jsonArray);
                }
            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                }
            });
            RequestManager.getInstance(SearchActivity.this).addToRequestQueue(searchRequest);
        }
    }
}
