package com.mobileapp.jolono.remora;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class ViewProfileActivity extends ActionBarActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("life cycle", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        findViewById(R.id.profilePageGroupButton).setOnClickListener(this);
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
            case R.id.profilePageGroupButton:
                startActivity(new Intent(this, ViewGroupActivity.class));
        }
    }

}
