package com.mobileapp.jolono.remora.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.model.Event;
import com.mobileapp.jolono.remora.model.Group;
import com.mobileapp.jolono.remora.model.RequestManager;

import org.json.JSONException;
import org.json.JSONObject;



public class CreateGroupActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView mEventName;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        
        mEventName = (TextView) findViewById(R.id.create_group_activity_event_name);
        
        mEventName.setText(getIntent().getStringExtra("eventName"));

        findViewById(R.id.activity_create_group_save_group).setOnClickListener(this);

        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_group, menu);
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
            case R.id.activity_create_group_save_group:
                EditText editText = (EditText) findViewById(R.id.activity_create_group_name);
                String name = editText.getText().toString();
                editText = (EditText) findViewById(R.id.activity_create_group_description);
                String description = editText.getText().toString();

                Group group = new Group(name, description);
                JsonObjectRequest request = group.createRequest(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            final String groupId = response.getString("id");
                            final String eventId = getIntent().getStringExtra("eventId");
                            JsonObjectRequest addGroupRequest = Event.addGroupRequest(eventId, groupId, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    Intent intent = new Intent(CreateGroupActivity.this, GetEventActivity.class);
                                    intent.putExtra("id", eventId);
                                    startActivity(intent);
                                    finish();

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    finish();
                                }
                            });

                            RequestManager.getInstance(getApplicationContext()).addToRequestQueue(addGroupRequest);
                        } catch(JSONException e) {
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (volleyError instanceof ParseError) {
                           
                            startActivity(new Intent(CreateGroupActivity.this, GetEventActivity.class));
                            finish();
                        }
                    }
                });

                RequestManager.getInstance(this).addToRequestQueue(request);
        }
        
    }
}
