package com.mobileapp.jolono.remora;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class GetAccountActivity extends ActionBarActivity implements AccountCredentialsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_account);

        //UserAccount.login("torrance.8@osu.edu", "password"); //TODO: GET RID OF THIS.
        //create fragments and add to activity layout
        //if(!UserAccount.verify()) Log.e("AUTH", "failed account verification.");//TODO: DO MORE
        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();

        Fragment accountCredFrag = AccountCredentialsFragment.newInstance(UserAccount.mAccountName);
        fragTrans.add(R.id.activity_get_account_fragment_container, accountCredFrag);

        Profile profile = Profile.getSelectedProfile(UserAccount.mUserId);
        Fragment profileFrag = ProfileFragment.newInstance(profile.mName, profile.mAge, profile.mGender, profile.mDescription);
        fragTrans.add(R.id.activity_get_account_fragment_container, profileFrag);

        fragTrans.commit();
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
