package com.mobileapp.jolono.remora.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.model.Profile;
import com.mobileapp.jolono.remora.model.RequestManager;
import com.mobileapp.jolono.remora.model.UserAccount;

import org.json.JSONObject;


/**

 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    public Profile mProfile;
    public boolean mIsUser = false;

    public static ProfileFragment newInstance(Profile profile, boolean isUser) {
        ProfileFragment profileFragment = new ProfileFragment();
        profileFragment.mProfile = profile;
        profileFragment.mIsUser = isUser;

        return profileFragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        if(mIsUser) {
           View descriptionView = v.findViewById(R.id.fragment_profile_description);
           descriptionView.setFocusable(true);
           descriptionView.setEnabled(true);
           descriptionView.setFocusableInTouchMode(true);

           View saveButtonView = v.findViewById(R.id.fragment_profile_save_button);
           saveButtonView.setOnClickListener(this);
        }
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mProfile != null) {
            //display text
            ((TextView) getView().findViewById(R.id.fragment_profile_name)).setText(mProfile.mName);
            ((TextView) getView().findViewById(R.id.fragment_profile_age)).setText(Integer.toString(mProfile.mAge));
            ((TextView) getView().findViewById(R.id.fragment_profile_gender)).setText(mProfile.mGender);
            ((TextView) getView().findViewById(R.id.fragment_profile_description)).setText(mProfile.mDescription);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fragment_profile_save_button) {
            EditText description = (EditText) getView().findViewById(R.id.fragment_profile_description);

            mProfile.mName = description.getText().toString();
            JsonObjectRequest request = mProfile.editProfileRequest(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Log.d("good?", "good?");
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("shit", volleyError.getCause().toString());
                }
            });

            RequestManager.getInstance(getActivity()).addToRequestQueue(request);
        }
    }
}
