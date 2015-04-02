package com.mobileapp.jolono.remora.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.model.UserAccount;

import org.json.JSONObject;


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

        editText = (EditText) findViewById(R.id.activity_create_account_name);
        savedInstanceState.putString("name", editText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        EditText editText = (EditText) findViewById(R.id.activity_create_account_email);
        editText.setText(savedInstanceState.getString("account_name"));
        editText = (EditText) findViewById(R.id.activity_create_account_name);
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
                JsonObjectRequest request = UserAccount.createAccountRequest(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject request) {
                        startActivity(new Intent(CreateAccountActivity.this, GetAccountActivity.class));
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if(volleyError instanceof ParseError) {
                            startActivity(new Intent(CreateAccountActivity.this, GetAccountActivity.class));
                            finish();
                        }
                    }
                });
        }
    }
}
