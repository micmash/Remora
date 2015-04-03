package com.mobileapp.jolono.remora.fragment.event;

import android.app.Activity;
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

import com.mobileapp.jolono.remora.activity.GetEventActivity;
import com.mobileapp.jolono.remora.model.Event;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * interface.
 *
 * TODO: use AbstractJsonBackedObject instead so only one fragment is needed.
 */
public class EventListFragment extends Fragment implements AbsListView.OnItemClickListener {

    public List<Event> mEvents;

    // TODO: Rename and change types of parameters
    public static EventListFragment newInstance(List<Event> events) {

        EventListFragment fragment = new EventListFragment();
        fragment.mEvents = events;
        return fragment;
    }

    private AbsListView mListView;
    private ListAdapter mAdapter;

    public EventListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ArrayAdapter<Event>(getActivity(),
                android.R.layout.simple_list_item_1, mEvents);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eventlist, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent getEventIntent = new Intent(getActivity(), GetEventActivity.class);
        getEventIntent.putExtra("id", Integer.toString(mEvents.get(position).getID()));

        startActivity(getEventIntent);
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
