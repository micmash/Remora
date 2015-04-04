package com.mobileapp.jolono.remora.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.model.Event;
import com.mobileapp.jolono.remora.model.RequestManager;

import org.json.JSONObject;

public class CreateEventActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        findViewById(R.id.activity_create_event_create_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.activity_create_event_name);
                String name = editText.getText().toString();
                editText = (EditText) findViewById(R.id.activity_create_event_description);
                String description = editText.getText().toString();
                editText = (EditText) findViewById(R.id.activity_create_event_start_time);
                String starttime = editText.getText().toString();
                editText = (EditText) findViewById(R.id.activity_create_event_end_time);
                String endtime = editText.getText().toString();
                editText = (EditText) findViewById(R.id.activity_create_event_name);
                String location = editText.getText().toString();

                Event event = new Event(name, location, description, starttime, endtime);
                JsonObjectRequest createRequest = event.createRequest(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Event createdEvent = new Event(jsonObject);

                        Intent getEventIntent = new Intent(CreateEventActivity.this, GetEventActivity.class);
                        getEventIntent.putExtra("id", Integer.toString(createdEvent.getID()));
                        startActivity(getEventIntent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

                RequestManager.getInstance(CreateEventActivity.this).addToRequestQueue(createRequest);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create, menu);
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