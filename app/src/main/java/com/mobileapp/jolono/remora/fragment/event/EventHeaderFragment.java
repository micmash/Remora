package com.mobileapp.jolono.remora.fragment.event;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.activity.CreateAccountActivity;
import com.mobileapp.jolono.remora.activity.CreateGroupActivity;
import com.mobileapp.jolono.remora.model.Event;
import com.mobileapp.jolono.remora.model.RequestManager;

import org.json.JSONObject;

/**

 */
public class EventHeaderFragment extends Fragment {
    public Event mEvent;

    public static EventHeaderFragment newInstance(Event event) {
        EventHeaderFragment fragment = new EventHeaderFragment();
        fragment.mEvent = event;
        return fragment;
    }

    public EventHeaderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event_header, container, false);
        if(mEvent != null) {
            ((EditText) v.findViewById(R.id.fragment_event_header_name)).setText(mEvent.getName());
            ((EditText) v.findViewById(R.id.fragment_event_header_start_time)).setText(mEvent.getStartTime());
            ((EditText) v.findViewById(R.id.fragment_event_header_end_time)).setText(mEvent.getEndTime());
            ((EditText) v.findViewById(R.id.fragment_event_header_description)).setText(mEvent.getDescription());

            (v.findViewById(R.id.fragment_event_header_save_button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JsonObjectRequest request = mEvent.editRequest(new Response.Listener<JSONObject>() {
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

            (v.findViewById(R.id.fragment_event_header_add_group_button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CreateGroupActivity.class);
                    intent.putExtra("eventName", mEvent.getName());
                    intent.putExtra("eventId", 3); //TODO: get id.
                    startActivity(intent);
                }

            });
        }

        return v;
    }
}
