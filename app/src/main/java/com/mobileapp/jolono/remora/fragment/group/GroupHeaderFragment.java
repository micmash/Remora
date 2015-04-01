package com.mobileapp.jolono.remora.fragment.group;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapp.jolono.remora.R;
import com.mobileapp.jolono.remora.model.Group;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link GroupHeaderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupHeaderFragment extends Fragment {
    public Group mGroup;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group_header, container, false);
    }



}
