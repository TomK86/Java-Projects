package com.tkelly.splitthebill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;

public class SplitFragment extends Fragment {

    private static final String ARG_TAX = "tax";

    private MyApplication mApp;
    private double mTax;
    private TextView mPayerCountText, mItemCountText, mResultText;
    private EditText mTaxEdit;
    private Button mPayerListBtn, mItemListBtn, mSplitBtn;
    private OnButtonPressedListener mListener;

    public static SplitFragment newInstance(double tax) {
        SplitFragment fragment = new SplitFragment();
        Bundle args = new Bundle();
        args.putDouble(ARG_TAX, tax);
        fragment.setArguments(args);
        return fragment;
    }

    public SplitFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApp = (MyApplication) getActivity().getApplication();

        if (getArguments() != null) {
            mTax = getArguments().getDouble(ARG_TAX, -1d);
        } else if (savedInstanceState != null) {
            mTax = savedInstanceState.getDouble(ARG_TAX, -1d);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment_view = inflater.inflate(R.layout.fragment_split, container, false);
        mPayerCountText = (TextView) fragment_view.findViewById(R.id.payer_count_text);
        mItemCountText = (TextView) fragment_view.findViewById(R.id.item_count_text);
        mTaxEdit = (EditText) fragment_view.findViewById(R.id.tax_edit);
        mResultText = (TextView) fragment_view.findViewById(R.id.result_text);
        mPayerListBtn = (Button) fragment_view.findViewById(R.id.payer_list_btn);
        mItemListBtn = (Button) fragment_view.findViewById(R.id.item_list_btn);
        mSplitBtn = (Button) fragment_view.findViewById(R.id.split_btn);
        return fragment_view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String payer_count = String.format(getResources().getString(R.string.payer_count),
                mApp.getNumPayers());
        mPayerCountText.setText(payer_count);

        String item_count = String.format(getResources().getString(R.string.item_count),
                mApp.getNumItems());
        mItemCountText.setText(item_count);

        if (mTax >= 1d) {
            mTaxEdit.setText(NumberFormat.getNumberInstance().format((mTax - 1d) * 100d));
        }

        String result = "";
        for (Payer p : mApp.getPayers()) {
            if (p.getAmtOwed() > 0d) {
                String before_tax = NumberFormat.getCurrencyInstance()
                        .format(p.getAmtOwed());
                String after_tax = NumberFormat.getCurrencyInstance()
                        .format(p.getAmtOwed() * mTax);
                result += p.getName() + " owes " + after_tax + " (" +
                        before_tax + " before tax)\n";
            }
        }
        if (!result.isEmpty()) {
            result += "\nDon't forget to tip your server!";
        }
        mResultText.setText(result);

        mPayerListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(),
                        PayerListActivity.class));
            }
        });

        mItemListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(),
                        ItemListActivity.class));
            }
        });

        mSplitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double tax = Double.parseDouble(mTaxEdit.getText().toString());
                    if (mApp.payersIsEmpty()) {
                        makeToast(R.string.error_payers_empty);
                    } else if (mApp.itemsIsEmpty()) {
                        makeToast(R.string.error_items_empty);
                    } else if (tax < 0d) {
                        makeToast(R.string.error_tax_negative);
                    } else {
                        mTax = (tax / 100d) + 1d;
                        mApp.setTax(mTax);
                        onButtonPressed("item_select");
                    }
                } catch (NumberFormatException e) {
                    makeToast(R.string.error_tax_invalid);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        String payer_count = String.format(getResources().getString(R.string.payer_count),
                mApp.getNumPayers());
        mPayerCountText.setText(payer_count);

        String item_count = String.format(getResources().getString(R.string.item_count),
                mApp.getNumItems());
        mItemCountText.setText(item_count);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble(ARG_TAX, mTax);
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

    protected void makeToast(int s) {
        Toast.makeText(getActivity().getApplicationContext(),
                getResources().getString(s),
                Toast.LENGTH_SHORT).show();
    }

}