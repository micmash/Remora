package com.mobileapp.jolono.remora.fragment.group;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.model.Group;
import com.mobileapp.jolono.remora.model.RequestManager;

import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link GroupHeaderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupHeaderFragment extends Fragment {
    public Group mGroup;
    
    private TextView mGroupName;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment GroupHeaderFragment.
     */
    public static GroupHeaderFragment newInstance(Group group) {
        GroupHeaderFragment fragment = new GroupHeaderFragment();
        fragment.mGroup = group;
        return fragment;
    }

    public GroupHeaderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mGroupName.setText("Group Name");

    }


    @Override
    public void onStart() {
        super.onStart();
        mGroupName = ((TextView) getView().findViewById(R.id.group_header_group_name));
        mGroupName.setText(mGroup.getName());

        ((TextView) getView().findViewById(R.id.group_header_group_description)).setText(mGroup.getDescription());

    }
    
    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        //bundle.putString("mGroupName", mGroupName.getText().toString());
        
    }
    
    @Override
    public void onActivityCreated(Bundle toLoad) {
        super.onActivityCreated(toLoad);
        //mGroupName.setText(toLoad.get("mGroupName").toString());
        
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_group_header, container, false);
        view.findViewById(R.id.fragment_group_header_save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) view.findViewById(R.id.group_header_group_name);
                String name = editText.getText().toString();
                editText = (EditText) view.findViewById(R.id.group_header_group_description);
                String description = editText.getText().toString();

                if(name.isEmpty() || description.isEmpty()) return;

                mGroup.setName(name);
                mGroup.setDescription(description);

                mGroup.mObjUrl = "http://ec2-52-0-168-55.compute-1.amazonaws.com/groups/" + mGroup.getID() + ".json"; //TODO: get url not hardcoded.
                JsonObjectRequest request = mGroup.editRequest(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        //don't need to edit
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //TODO: revert?
                    }
                });

                RequestManager.getInstance(getActivity()).addToRequestQueue(request);
            }
        });

        return  view;
    }



}
