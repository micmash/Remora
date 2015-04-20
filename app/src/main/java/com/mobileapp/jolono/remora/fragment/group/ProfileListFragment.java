package com.mobileapp.jolono.remora.fragment.group;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.mobileapp.jolono.remora.R;

import com.mobileapp.jolono.remora.activity.GetGroupActivity;
import com.mobileapp.jolono.remora.activity.GetProfileActivity;
import com.mobileapp.jolono.remora.model.Group;
import com.mobileapp.jolono.remora.model.Profile;
import com.mobileapp.jolono.remora.view.ListItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * A
 * interface.
 */
public class ProfileListFragment extends Fragment implements View.OnClickListener {
    public Group mGroup;
    public List<ListItemView> mListItemViews;

    public static ProfileListFragment newInstance(Context context, Group group) {
        ProfileListFragment fragment = new ProfileListFragment();
        fragment.mGroup = group;
        fragment.mListItemViews = new ArrayList<>(group.mMembers.size());
        for(int i = 0; i < group.mMembers.size(); ++i) {
            fragment.mListItemViews.add(new ListItemView(context));
        }
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProfileListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        if(mGroup != null) {
            view = inflater.inflate(R.layout.fragment_profile_list, container, false);
            for(int i = 0; i < mListItemViews.size(); ++i) {
                ListItemView listItemView = mListItemViews.get(i);
                listItemView.position = i;
                listItemView.setOnClickListener(this);
                listItemView.setText(mGroup.mMembers.get(i).toString());
                listItemView.setTextAppearance(getActivity(), R.style.RemoraTheme_ListItem);
                listItemView.setBackgroundColor(getResources().getColor(R.color.list_item_background_color));
                LinearLayout linLayout = (LinearLayout) view.findViewById(R.id.fragment_profile_list_base);
                linLayout.addView(listItemView);
            }
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        ListItemView listItemView = (ListItemView) v;

        Intent getProfileIntent = new Intent(getActivity(), GetProfileActivity.class);
        String profileId = Integer.toString(mGroup.mMembers.get(listItemView.position).getID());
        getProfileIntent.putExtra("id", profileId);

        startActivity(getProfileIntent);
    }
}
