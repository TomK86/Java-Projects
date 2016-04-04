package com.tkelly.splitthebill;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ItemSelectFragment extends Fragment {

    private static final String ARG_QUERY = "query";

    private String mQuery;
    private OnItemSelectListener mSelectListener;
    private OnFinishListener mFinishListener;
    private ListView mListView;
    private ItemSelectAdapter mAdapter;
    private TextView mQueryText;
    private Button mSubmitBtn;

    public static ItemSelectFragment newInstance(String query) {
        ItemSelectFragment fragment = new ItemSelectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }

    public ItemSelectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication app = (MyApplication) getActivity().getApplication();

        if (getArguments() != null) {
            mQuery = getArguments().getString(ARG_QUERY);
        } else if (savedInstanceState != null) {
            mQuery = savedInstanceState.getString(ARG_QUERY);
        }

        mAdapter = new ItemSelectAdapter(getContext(), R.layout.content_item_row, app.getItems());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment_view = inflater.inflate(R.layout.fragment_item_select, container, false);
        mListView = (ListView) fragment_view.findViewById(android.R.id.list);
        mQueryText = (TextView) fragment_view.findViewById(R.id.query_text);
        mSubmitBtn = (Button) fragment_view.findViewById(R.id.submit_btn);
        return fragment_view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyApplication app = (MyApplication) getActivity().getApplication();

        mListView.setAdapter(mAdapter);

        mQueryText.setText(mQuery);

        boolean all_items_complete = true;
        for (Item item : app.getItems()) {
            all_items_complete &= item.isCompleted();
        }

        if (all_items_complete) {
            mSubmitBtn.setClickable(true);
            mSubmitBtn.setVisibility(View.VISIBLE);
        } else {
            mSubmitBtn.setClickable(false);
            mSubmitBtn.setVisibility(View.GONE);
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onItemSelect(position);
            }
        });

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinish();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARG_QUERY, mQuery);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mSelectListener = (OnItemSelectListener) getActivity();
            mFinishListener = (OnFinishListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnItemSelectListener and OnButtonPressedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mSelectListener = null;
        mFinishListener = null;
    }

    public void onItemSelect(int idx) {
        if (mSelectListener != null) {
            mSelectListener.onItemSelect(idx);
        }
    }

    public void onFinish() {
        if (mFinishListener != null) {
            mFinishListener.onFinish();
        }
    }

    public interface OnItemSelectListener {
        void onItemSelect(int idx);
    }

    public interface OnFinishListener {
        void onFinish();
    }

}
