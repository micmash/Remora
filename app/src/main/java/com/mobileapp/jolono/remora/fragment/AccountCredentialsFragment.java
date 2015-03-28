package com.mobileapp.jolono.remora.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileapp.jolono.remora.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AccountCredentialsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AccountCredentialsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountCredentialsFragment extends Fragment implements View.OnClickListener {
    private static final String ACCOUNT_NAME = "accountName";

    private String mAccountName;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param accountName account name.
     * @return A new instance of fragment AccountCredentialsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountCredentialsFragment newInstance(String accountName) {
        AccountCredentialsFragment fragment = new AccountCredentialsFragment();
        Bundle args = new Bundle();
        args.putString(ACCOUNT_NAME, accountName);
        fragment.setArguments(args);
        return fragment;
    }

    public AccountCredentialsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAccountName = getArguments().getString(ACCOUNT_NAME);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        ((TextView) getView().findViewById(R.id.fragment_account_credentials_email)).setText(mAccountName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_credentials, container, false);
        getView().findViewById(R.id.fragment_account_credentials_change_password_button).setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onClick(View v) {
        Log.d("sdaf", "clicked!!!!!");
        if (mListener != null && v.getId() == R.id.fragment_account_credentials_change_password_button) {
            mListener.onChangePassword();
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onChangePassword();
    }

}
