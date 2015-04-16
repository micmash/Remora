package com.mobileapp.jolono.remora.fragment.group;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.mobileapp.jolono.remora.R;

import com.mobileapp.jolono.remora.activity.GetProfileActivity;
import com.mobileapp.jolono.remora.model.Group;
import com.mobileapp.jolono.remora.model.Profile;

/**
 * A
 * interface.
 */
public class ProfileListFragment extends Fragment implements AbsListView.OnItemClickListener {
    private Group mGroup;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    public static ProfileListFragment newInstance(Group group) {
        ProfileListFragment fragment = new ProfileListFragment();
        fragment.mGroup = group;
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProfileListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(mGroup != null) {
            mAdapter = new ArrayAdapter<Profile>(getActivity(),
                    android.R.layout.simple_list_item_1, mGroup.mMembers);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = null;
        if(mGroup != null) {
            view = inflater.inflate(R.layout.fragment_groupmember, container, false);
            // Set the adapter
            mListView = (AbsListView) view.findViewById(android.R.id.list);
            ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

            // Set OnItemClickListener so we can be notified on item clicks
            mListView.setOnItemClickListener(this);
        }
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent getProfileIntent = new Intent(getActivity(), GetProfileActivity.class);
        String sId = Integer.toString(mGroup.mMembers.get(position).getID());
        getProfileIntent.putExtra("id", sId);
       
        startActivity(getProfileIntent);
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }
}
