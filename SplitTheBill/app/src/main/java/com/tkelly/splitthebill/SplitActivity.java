package com.tkelly.splitthebill;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class SplitActivity extends AppCompatActivity
        implements SplitFragment.OnSplitButtonPressedListener,
        NumberQueryFragment.OnNumberPickedListener,
        PayerSelectFragment.OnPayerSelectListener,
        ItemSelectFragment.OnItemSelectListener,
        ItemSelectFragment.OnFinishListener {

    private static final String ARG_SELECTED = "selected";
    private static final String ARG_RESELECTED = "reselected";
    private static final String ARG_CURRENT_ITEM = "current_item";
    private static final String ARG_CURRENT_QTY = "current_qty";
    private static final String ARG_INPUT_QTY = "input_qty";

    protected static final String ID_SPLIT = "split";
    protected static final String ID_ITEM_SELECT = "item_select";
    protected static final String ID_PAYER_SELECT = "payer_select";
    protected static final String ID_PAYER_RESELECT = "payer_reselect";
    protected static final String ID_NUMBER_QUERY = "number_query";

    private MyApplication mApp;
    private FragmentManager mMgr;
    private ArrayList<Integer> mSelected, mReselected;
    private int mCurrentItem, mCurrentQty, mInputQty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_activity_split);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mApp = (MyApplication) getApplication();
        mMgr = getSupportFragmentManager();

        if (savedInstanceState == null) {
            mSelected = new ArrayList<>();
            mReselected = new ArrayList<>();
            mCurrentItem = -1;
            mCurrentQty = 0;
            mInputQty = 0;
            mApp.clearAmtsOwed();
            mApp.clearAllPayments();
            mApp.clearCompleted();
            clearBackStack();
            startFragment(ID_SPLIT);
        } else {
            mSelected = arrayToList(savedInstanceState.getIntArray(ARG_SELECTED));
            mReselected = arrayToList(savedInstanceState.getIntArray(ARG_RESELECTED));
            mCurrentItem = savedInstanceState.getInt(ARG_CURRENT_ITEM);
            mCurrentQty = savedInstanceState.getInt(ARG_CURRENT_QTY);
            mInputQty = savedInstanceState.getInt(ARG_INPUT_QTY);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putIntArray(ARG_SELECTED, listToArray(mSelected));
        outState.putIntArray(ARG_RESELECTED, listToArray(mReselected));
        outState.putInt(ARG_CURRENT_ITEM, mCurrentItem);
        outState.putInt(ARG_CURRENT_QTY, mCurrentQty);
        outState.putInt(ARG_INPUT_QTY, mInputQty);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mMgr.getBackStackEntryCount() > 1) {
                    mMgr.popBackStack();
                    return true;
                } else {
                    return super.onOptionsItemSelected(item);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSplitButtonPressed() {
        mCurrentItem = -1;
        mCurrentQty = 0;
        mInputQty = 0;
        startFragment(ID_ITEM_SELECT);
    }

    @Override
    public void onNumberPicked(int n) {
        mInputQty = n;
        startFragment(ID_PAYER_RESELECT);
    }

    @Override
    public void onPayerSelect(boolean[] checked, boolean init) {
        if (init) {
            mSelected.clear();
            for (int i = 0; i < checked.length; i++) {
                if (checked[i]) { mSelected.add(i); }
            }
            paySelectedIfOk();
        } else {
            mReselected.clear();
            for (int i = 0; i < checked.length; i++) {
                if (checked[i]) { mReselected.add(mSelected.get(i)); }
            }
            payReselected();
        }
    }

    @Override
    public void onItemSelect(int idx) {
        Item item = mApp.getItem(idx);
        mCurrentItem = idx;
        mCurrentQty = item.getQty();
        item.setCompleted(false);
        item.clearPayments();
        startFragment(ID_PAYER_SELECT);
    }

    @Override
    public void onFinish() {
        mCurrentItem = -1;
        mCurrentQty = 0;
        mInputQty = 0;
        mSelected.clear();
        mReselected.clear();
        mApp.updateAmtsOwed();
        clearBackStack();
        startFragment(ID_SPLIT);
    }

    protected void startFragment(String fragment_id) {
        if (findViewById(R.id.fragment_container) != null) {
            String query;
            Resources res = getResources();
            Item curItem = null;
            if (mCurrentItem >= 0) { curItem = mApp.getItem(mCurrentItem); }

            switch (fragment_id) {
                case ID_SPLIT:
                    SplitFragment split = SplitFragment.newInstance(mApp.getTax());
                    mMgr.beginTransaction()
                            .replace(R.id.fragment_container, split, fragment_id)
                            .addToBackStack(fragment_id)
                            .commit();
                    break;
                case ID_ITEM_SELECT:
                    if (mCurrentItem == -1) {
                        query = res.getString(R.string.query_item_select_first);
                    } else {
                        query = res.getString(R.string.query_item_select_next);
                    }
                    ItemSelectFragment item_select = ItemSelectFragment.newInstance(query);
                    mMgr.beginTransaction()
                            .replace(R.id.fragment_container, item_select, fragment_id)
                            .addToBackStack(fragment_id)
                            .commit();
                    break;
                case ID_PAYER_SELECT:
                    if (curItem == null) { break; }
                    query = String.format(res.getString(R.string.query_payer_select),
                            curItem.getName());
                    PayerSelectFragment payer_select = PayerSelectFragment.newInstance(query,
                            new int[0], true);
                    mMgr.beginTransaction()
                            .replace(R.id.fragment_container, payer_select, fragment_id)
                            .addToBackStack(fragment_id)
                            .commit();
                    break;
                case ID_PAYER_RESELECT:
                    if (curItem == null) { break; }
                    if (mInputQty == 1) {
                        query = String.format(res.getString(R.string.query_payer_reselect_one),
                                curItem.getName());
                    } else {
                        query = String.format(res.getString(R.string.query_payer_reselect_more),
                                mInputQty, curItem.getName());
                    }
                    PayerSelectFragment payer_reselect = PayerSelectFragment.newInstance(query,
                            listToArray(mSelected), false);
                    mMgr.beginTransaction()
                            .add(R.id.fragment_container, payer_reselect, fragment_id)
                            .addToBackStack(fragment_id)
                            .commit();
                    break;
                case ID_NUMBER_QUERY:
                    if (curItem == null) { break; }
                    query = String.format(res.getString(R.string.query_number), curItem.getName());
                    NumberQueryFragment number_query = NumberQueryFragment.newInstance(query,
                            mCurrentQty, mCurrentItem);
                    mMgr.beginTransaction()
                            .add(R.id.fragment_container, number_query, fragment_id)
                            .addToBackStack(fragment_id)
                            .commit();
                    break;
                default:
                    break;
            }
        }
    }

    protected void paySelected() {
        if (mCurrentItem >= 0) {
            Item curItem = mApp.getItem(mCurrentItem);
            double costPerPayer = curItem.getCost() * mCurrentQty / mSelected.size();
            for (int i = 0; i < mSelected.size(); i++) {
                curItem.addPayment(mSelected.get(i), costPerPayer);
            }
            curItem.setCompleted(true);
            mCurrentQty = 0;
            mInputQty = 0;
            startFragment(ID_ITEM_SELECT);
        }
    }

    protected void paySelectedIfOk() {
        if (mCurrentItem >= 0) {
            Item curItem = mApp.getItem(mCurrentItem);
            curItem.clearPayments();
            mCurrentQty = curItem.getQty();
            if (mCurrentQty == 1 || mSelected.size() == 1) {
                paySelected();
            } else if (mCurrentQty == 2 && mSelected.size() == 2) {
                paySelected();
            } else {
                final Dialog dialog = new Dialog(SplitActivity.this);
                dialog.setContentView(R.layout.dialog_confirm);
                dialog.setTitle("Split evenly?");
                dialog.setCancelable(true);

                TextView query_text = (TextView) dialog.findViewById(R.id.query_text);
                String query = String.format(getResources().getString(R.string.query_even_split),
                        curItem.getQty(), curItem.getName(), mSelected.size());
                query_text.setText(query);

                Button yes_btn = (Button) dialog.findViewById(R.id.yes_btn);
                yes_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        paySelected();
                    }
                });

                Button no_btn = (Button) dialog.findViewById(R.id.no_btn);
                no_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        startFragment(ID_NUMBER_QUERY);
                    }
                });

                dialog.show();
            }
        }
    }

    protected void payReselected() {
        if (mCurrentItem >= 0) {
            Item curItem = mApp.getItem(mCurrentItem);
            double costPerPayer = curItem.getCost() * mInputQty / mReselected.size();
            for (int i = 0; i < mReselected.size(); i++) {
                curItem.addPayment(mReselected.get(i), costPerPayer);
            }
            mCurrentQty -= mInputQty;
            if (mCurrentQty == 0) {
                curItem.setCompleted(true);
                mInputQty = 0;
                startFragment(ID_ITEM_SELECT);
            } else {
                startFragment(ID_NUMBER_QUERY);
            }
        }
    }

    protected void clearBackStack() {
        while (mMgr.getBackStackEntryCount() > 0) {
            mMgr.popBackStackImmediate();
        }
    }

    protected int[] listToArray(ArrayList<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    protected ArrayList<Integer> arrayToList(int[] array) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array != null) {
            for (int i : array) {
                list.add(i);
            }
        }
        return list;
    }

}
