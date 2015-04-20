package com.mobileapp.jolono.remora.fragment.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.model.Profile;
import com.mobileapp.jolono.remora.model.RequestManager;

import org.json.JSONObject;


/**

 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
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
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        //if(UserAccount.mUserProfile == mProfile) {
        if(true) {
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
            ((TextView) getView().findViewById(R.id.fragment_profile_name)).setText(mProfile.getFirstName());
            ((TextView) getView().findViewById(R.id.fragment_profile_age)).setText(mProfile.getAge());
            ((TextView) getView().findViewById(R.id.fragment_profile_gender)).setText(mProfile.getGender());
            ((TextView) getView().findViewById(R.id.fragment_profile_description)).setText(mProfile.getDescription());
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fragment_profile_save_button) {
            EditText editText = (EditText) getView().findViewById(R.id.fragment_profile_name);
            String fName = editText.getText().toString();
            editText = (EditText) getView().findViewById(R.id.fragment_profile_description);
            String description = editText.getText().toString();

            mProfile.setFirstName(fName);
            mProfile.setDescription(description);

            
            JsonObjectRequest request = mProfile.editRequest(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                         Log.d("good?", "good?");
                     }
                 }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (!(volleyError instanceof ParseError)) {
                            Log.e("wtf", "wtf");
                        }
                    }
                });

            RequestManager.getInstance(getActivity()).addToRequestQueue(request);
        }
    }
}
