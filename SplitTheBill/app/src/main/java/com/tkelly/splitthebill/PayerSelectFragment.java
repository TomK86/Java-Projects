package com.tkelly.splitthebill;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class PayerSelectFragment extends Fragment {

    private static final String ARG_QUERY = "query";
    private static final String ARG_DISPLAYED = "displayed";
    private static final String ARG_INIT = "init";

    private String mQuery;
    private int[] mDisplayed;
    private boolean mInit;
    private PayerSelectAdapter mAdapter;
    private OnPayerSelectListener mListener;
    private ListView mListView;
    private TextView mQueryText;
    private Button mSubmitBtn;

    public static PayerSelectFragment newInstance(String query, int[] displayed, boolean init) {
        PayerSelectFragment fragment = new PayerSelectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);
        args.putIntArray(ARG_DISPLAYED, displayed);
        args.putBoolean(ARG_INIT, init);
        fragment.setArguments(args);
        return fragment;
    }

    public PayerSelectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication app = (MyApplication) getActivity().getApplication();

        if (getArguments() != null) {
            mQuery = getArguments().getString(ARG_QUERY);
            mDisplayed = getArguments().getIntArray(ARG_DISPLAYED);
            mInit = getArguments().getBoolean(ARG_INIT);
        } else if (savedInstanceState != null) {
            mQuery = savedInstanceState.getString(ARG_QUERY);
            mDisplayed = savedInstanceState.getIntArray(ARG_DISPLAYED);
            mInit = savedInstanceState.getBoolean(ARG_INIT);
        }

        if (mDisplayed == null || mDisplayed.length == 0) {
            mDisplayed = app.getAllPayerIndices();
        }

        ArrayList<Payer> displayed_payers = new ArrayList<>();
        for (int idx : mDisplayed) {
            displayed_payers.add(app.getPayer(idx));
        }

        mAdapter = new PayerSelectAdapter(getContext(),
                R.layout.content_payer_select_row, displayed_payers);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment_view = inflater.inflate(R.layout.fragment_payer_select, container, false);
        mListView = (ListView) fragment_view.findViewById(android.R.id.list);
        mQueryText = (TextView) fragment_view.findViewById(R.id.query_text);
        mSubmitBtn = (Button) fragment_view.findViewById(R.id.submit_btn);
        return fragment_view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView.setAdapter(mAdapter);

        mQueryText.setText(mQuery);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean[] checked = mAdapter.getChecked();
                boolean some_checked = false;
                for (boolean b : checked) {
                    some_checked |= b;
                }
                if (some_checked) {
                    onPayerSelect(checked);
                } else {
                    makeToast(R.string.error_no_payers_selected);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(ARG_QUERY, mQuery);
        outState.putIntArray(ARG_DISPLAYED, mDisplayed);
        outState.putBoolean(ARG_INIT, mInit);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnPayerSelectListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnPayerSelectListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onPayerSelect(boolean[] checked) {
        mListener.onPayerSelect(checked, mInit);
    }

    protected void makeToast(int s) {
        Toast.makeText(getActivity().getApplicationContext(),
                getResources().getString(s),
                Toast.LENGTH_SHORT).show();
    }

    public interface OnPayerSelectListener {
        void onPayerSelect(boolean[] checked, boolean init);
    }

}
