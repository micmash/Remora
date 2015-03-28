package com.mobileapp.jolono.remora.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mobileapp.jolono.remora.R;


public class ViewGroupActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("life cycle", "view group, onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        findViewById(R.id.groupPageMember1Button).setOnClickListener(this);
    }

    /* life cycle logging --------------------------------------------------------------------*/
    @Override
    protected void onStart() {
        Log.d("life cycle", "view group, onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("life cycle", "view group, onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("life cycle", "view group, onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("life cycle", "view group, onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d("life cycle", "view group, onRestart");
        super.onRestart();
    }

    @Override
    protected  void onDestroy() {
        Log.d("life cycle", "view group, onDestroy");
        super.onDestroy();
    }
    /*---------------------------------------------------------------------------------------*/

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

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.groupPageMember1Button:
                this.startActivity(new Intent(this, ViewProfileActivity.class));
        }
    }
}
