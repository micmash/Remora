package com.mobileapp.jolono.remora.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.model.Profile;


/**

 */
public class ProfileFragment extends Fragment {
    public Profile mProfile;

    public static ProfileFragment newInstance(Profile profile) {
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.mProfile = profile;

        return profileFragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        //display text
        ((TextView) getView().findViewById(R.id.fragment_profile_name)).setText(mProfile.mName);
        ((TextView) getView().findViewById(R.id.fragment_profile_age)).setText(Integer.toString(mProfile.mAge));
        ((TextView) getView().findViewById(R.id.fragment_profile_gender)).setText(mProfile.mGender);
        ((TextView) getView().findViewById(R.id.fragment_profile_description)).setText(mProfile.mDescription);
    }
}
