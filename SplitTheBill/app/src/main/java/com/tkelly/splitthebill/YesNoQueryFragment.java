package com.tkelly.splitthebill;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class YesNoQueryFragment extends Fragment {

    private static final String ARG_QUERY = "query";

    private String mQuery;
    private TextView mQueryText;
    private Button mYesBtn, mNoBtn;
    private OnButtonPressedListener mListener;

    public static YesNoQueryFragment newInstance(String query) {
        YesNoQueryFragment fragment = new YesNoQueryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }

    public YesNoQueryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mQuery = getArguments().getString(ARG_QUERY);
        } else if (savedInstanceState != null) {
            mQuery = savedInstanceState.getString(ARG_QUERY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment_view = inflater.inflate(R.layout.fragment_yes_no_query, container, false);
        mQueryText = (TextView) fragment_view.findViewById(R.id.query_text);
        mYesBtn = (Button) fragment_view.findViewById(R.id.yes_btn);
        mNoBtn = (Button) fragment_view.findViewById(R.id.no_btn);
        return fragment_view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mQueryText.setText(mQuery);

        mYesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed("pay_selected_now");
            }
        });

        mNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed("number_query");
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(ARG_QUERY, mQuery);
    }

    public void onButtonPressed(String id) {
        if (mListener != null) {
            mListener.onButtonPressed(id);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnButtonPressedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnButtonPressedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnButtonPressedListener {
        void onButtonPressed(String id);
    }

}
