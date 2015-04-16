package com.mobileapp.jolono.remora.fragment;

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

import com.mobileapp.jolono.remora.activity.GetGroupActivity;
import com.mobileapp.jolono.remora.model.Group;

import java.util.List;

/**
 */
public class GroupListFragment extends Fragment implements AbsListView.OnItemClickListener {
    public List<Group> mGroups;

    private AbsListView mListView;
    private ListAdapter mAdapter;

    public static GroupListFragment newInstance(List<Group> groups) {
        GroupListFragment fragment = new GroupListFragment();
        fragment.mGroups = groups;
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GroupListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(mGroups != null) {
            mAdapter = new ArrayAdapter<Group>(getActivity(),
                    android.R.layout.simple_list_item_1, mGroups);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        if(mGroups != null) {
            view = inflater.inflate(R.layout.fragment_event, container, false);

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
        Intent getGroupIntent = new Intent(getActivity(), GetGroupActivity.class);
        getGroupIntent.putExtra("id", Integer.toString(mGroups.get(position).getID()));

        startActivity(getGroupIntent);
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
