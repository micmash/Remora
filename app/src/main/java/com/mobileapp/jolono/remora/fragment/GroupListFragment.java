package com.mobileapp.jolono.remora.fragment;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
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
import com.mobileapp.jolono.remora.model.Group;
import com.mobileapp.jolono.remora.view.ListItemView;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class GroupListFragment extends Fragment implements View.OnClickListener {
    public List<Group> mGroups;
    public List<ListItemView> mListItemViews;

    public static GroupListFragment newInstance(Context context, List<Group> groups) {
        GroupListFragment fragment = new GroupListFragment();
        fragment.mGroups = groups;
        fragment.mListItemViews = new ArrayList<>(groups.size());
        for(int i = 0; i < groups.size(); ++i) {
            fragment.mListItemViews.add(new ListItemView(context));
        }
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GroupListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        if(mGroups != null) {
            view = inflater.inflate(R.layout.fragment_group_list, container, false);
//            for(int i = 0; i < mListItemViews.size(); ++i) {
//                ListItemView listItemView = mListItemViews.get(i);
//                listItemView.position = i;
//                listItemView.setOnClickListener(this);
//                listItemView.setText(mGroups.get(i).toString());
//                listItemView.setTextAppearance(getActivity(), R.style.RemoraTheme_ListItem);
//                listItemView.setBackgroundColor(getResources().getColor(R.color.list_item_background_color));
//                LinearLayout linLayout = (LinearLayout) view.findViewById(R.id.fragment_group_list_base);
//                linLayout.addView(listItemView);
//            }
            CreateListAsyncTask task = new CreateListAsyncTask(getActivity(), this, view, mGroups, getResources());
            task.doInBackground(mListItemViews);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        ListItemView listItemView = (ListItemView) v;

        Intent getGroupIntent = new Intent(getActivity(), GetGroupActivity.class);
        String groupId = Integer.toString(mGroups.get(listItemView.position).getID());
        getGroupIntent.putExtra("id", groupId);

        startActivity(getGroupIntent);
    }
}

class CreateListAsyncTask extends AsyncTask<List<ListItemView>, Integer, View> {
    public Activity mActivity;
    public View.OnClickListener mClickListener;
    public View mBaseView;
    public List<Group> mGroups;
    public Resources mResources;

    public CreateListAsyncTask(Activity activity, View.OnClickListener clickListener, View view, List<Group> groups, Resources resources) {
        mActivity = activity;
        mClickListener = clickListener;
        mBaseView = view;
        mGroups = groups;
        mResources = resources;
    }
    protected View doInBackground(List<ListItemView>... views) {
        for(int i = 0; i < views[0].size(); ++i) {
            ListItemView listItemView =  views[0].get(i);
            listItemView.position = i;
            listItemView.setOnClickListener(mClickListener);

            listItemView.setText(mGroups.get(i).toString());
            listItemView.setTextAppearance(mActivity, R.style.RemoraTheme_ListItem);
            listItemView.setBackgroundColor(mResources.getColor(R.color.list_item_background_color));
            LinearLayout linLayout = (LinearLayout) mBaseView.findViewById(R.id.fragment_group_list_base);
            linLayout.addView(listItemView);
        }

        return mBaseView;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(View v) {
    }
}
