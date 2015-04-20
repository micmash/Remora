package com.mobileapp.jolono.remora.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.model.Profile;
import com.mobileapp.jolono.remora.model.RequestManager;
import com.mobileapp.jolono.remora.model.UserAccount;

import org.json.JSONObject;

import java.util.UUID;


public class CreateAccountActivity extends ActionBarActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Button acceptButton = (Button) findViewById(R.id.activity_create_account_accept_button);
        acceptButton.setOnClickListener(this);
    }

    @Override
    protected  void onSaveInstanceState(Bundle savedInstanceState) {
        EditText editText = (EditText) findViewById(R.id.activity_create_account_email);
        savedInstanceState.putString("account_name", editText.getText().toString());

        editText = (EditText) findViewById(R.id.activity_create_account_f_name);
        savedInstanceState.putString("name", editText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        EditText editText = (EditText) findViewById(R.id.activity_create_account_email);
        editText.setText(savedInstanceState.getString("account_name"));
        editText = (EditText) findViewById(R.id.activity_create_account_l_name);
        editText.setText(savedInstanceState.getString("name"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account, menu);
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
        switch (v.getId()) {
            case R.id.activity_create_account_accept_button:
                EditText editText = (EditText) findViewById(R.id.activity_create_account_email);
                UserAccount.mAccountName = editText.getText().toString();
                //profile fields
                editText = (EditText) findViewById(R.id.activity_create_account_f_name);
                String fname = editText.getText().toString();
                editText = (EditText) findViewById(R.id.activity_create_account_l_name);
                String lname = editText.getText().toString();
                editText = (EditText) findViewById(R.id.activity_create_account_birthdate);
                String birthdate = editText.getText().toString();
                editText = (EditText) findViewById(R.id.activity_create_account_gender);
                String gender = editText.getText().toString();
                

                //create profile.
                editText = (EditText) findViewById(R.id.activity_create_account_password);
                final String p_word = editText.getText().toString(); //this is fucking retarded
                final UUID uuid = UUID.randomUUID();
                final Profile profile = new Profile(fname, lname, birthdate, gender, uuid);

                JsonObjectRequest createProfileRequest = profile.createRequest(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        UserAccount.mUID = uuid.toString();
                        //create account
                        JsonObjectRequest createAccountRequest = UserAccount.createAccountRequest(p_word, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject request) {
                                Intent getAccountIntent = new Intent(CreateAccountActivity.this, GetAccountActivity.class);
                                getAccountIntent.putExtra("username", UserAccount.mAccountName);
                                startActivity(getAccountIntent);
                                finish();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                finish();
                            }
                        });

                        RequestManager.getInstance(CreateAccountActivity.this).addToRequestQueue(createAccountRequest);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                });


                RequestManager.getInstance(this).addToRequestQueue(createProfileRequest);
        }
    }
}
