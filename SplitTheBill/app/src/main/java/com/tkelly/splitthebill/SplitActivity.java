package com.tkelly.splitthebill;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import java.util.ArrayList;

public class SplitActivity extends FragmentActivity
        implements SplitFragment.OnButtonPressedListener,
        YesNoQueryFragment.OnButtonPressedListener,
        NumberQueryFragment.OnNumberPickedListener,
        PayerSelectFragment.OnPayerSelectListener,
        ItemSelectFragment.OnButtonPressedListener,
        ItemSelectFragment.OnItemSelectListener {

    private static final String ARG_SELECTED = "selected";
    private static final String ARG_RESELECTED = "reselected";
    private static final String ARG_CURRENT_ITEM = "current_item";
    private static final String ARG_CURRENT_QTY = "current_qty";
    private static final String ARG_INPUT_QTY = "input_qty";

    private MyApplication mApp;
    private ArrayList<Integer> mSelected, mReselected;
    private int mCurrentItem, mCurrentQty, mInputQty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split);

        mApp = (MyApplication) getApplication();
        mApp.clearAmtsOwed();
        mApp.clearCompleted();

        if (savedInstanceState == null) {
            mSelected = new ArrayList<>();
            mReselected = new ArrayList<>();
            mCurrentItem = -1;
            mCurrentQty = 0;
            mInputQty = 0;
            startFragment("split");
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
    public void onButtonPressed(String id) {
        if (findViewById(R.id.fragment_container) != null) {
            double costPerPayer;
            Item curItem = null;
            if (mCurrentItem >= 0) { curItem = mApp.getItem(mCurrentItem); }

            switch (id) {
                case "split":
                    startFragment(id);
                    break;
                case "item_select":
                    startFragment(id);
                    break;
                case "payer_select":
                    startFragment(id);
                    break;
                case "payer_reselect":
                    startFragment(id);
                    break;
                case "yes_no_query":
                    startFragment(id);
                    break;
                case "number_query":
                    startFragment(id);
                    break;
                case "pay_selected_now":
                    if (curItem == null) { break; }
                    costPerPayer = curItem.getCost() * mCurrentQty / mSelected.size();
                    mApp.updateAmtsOwed(mSelected, costPerPayer);
                    startFragment("item_select");
                    break;
                case "pay_selected_if_obvious":
                    if (curItem == null) { break; }
                    if (mCurrentQty == 1 || mSelected.size() == 1) {
                        costPerPayer = curItem.getCost() * mCurrentQty / mSelected.size();
                        mApp.updateAmtsOwed(mSelected, costPerPayer);
                        startFragment("item_select");
                    } else {
                        startFragment("yes_no_query");
                    }
                    break;
                case "pay_reselected":
                    if (curItem == null) { break; }
                    costPerPayer = curItem.getCost() * mInputQty / mReselected.size();
                    mApp.updateAmtsOwed(mReselected, costPerPayer);
                    mCurrentQty -= mInputQty;
                    if (mCurrentQty == 0) {
                        mInputQty = 0;
                        startFragment("item_select");
                    } else {
                        startFragment("number_query");
                    }
                    break;
                case "finish":
                    mCurrentItem = -1;
                    mCurrentQty = 0;
                    mInputQty = 0;
                    mSelected.clear();
                    mReselected.clear();
                    clearBackStack();
                    startFragment("split");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onPayerSelect(boolean[] checked, boolean init) {
        if (init) {
            mSelected.clear();
            for (int i = 0; i < checked.length; i++) {
                if (checked[i]) { mSelected.add(i); }
            }
            onButtonPressed("pay_selected_if_obvious");
        } else {
            mReselected.clear();
            for (int i = 0; i < checked.length; i++) {
                if (checked[i]) { mReselected.add(mSelected.get(i)); }
            }
            onButtonPressed("pay_reselected");
        }
    }

    @Override
    public void onItemSelect(int idx) {
        mCurrentItem = idx;
        mCurrentQty = mApp.getItem(idx).getQty();
        startFragment("payer_select");
    }

    @Override
    public void onNumberPicked(int n) {
        mInputQty = n;
        startFragment("payer_reselect");
    }

    public void startFragment(String fragment_id) {
        if (findViewById(R.id.fragment_container) != null) {
            String query;
            Resources res = getResources();
            Item curItem = null;
            if (mCurrentItem >= 0) { curItem = mApp.getItem(mCurrentItem); }

            switch (fragment_id) {
                case "split":
                    SplitFragment split = SplitFragment.newInstance(mApp.getTax());
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, split)
                            .addToBackStack(null).commit();
                    break;
                case "item_select":
                    if (curItem == null) {
                        mApp.clearAmtsOwed();
                        mApp.clearCompleted();
                    } else {
                        curItem.setCompleted(true);
                    }
                    mCurrentQty = 0;
                    mInputQty = 0;
                    if (mCurrentItem == -1) {
                        query = res.getString(R.string.query_item_select_first);
                    } else {
                        query = res.getString(R.string.query_item_select_next);
                    }
                    ItemSelectFragment item_select = ItemSelectFragment.newInstance(query);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, item_select)
                            .addToBackStack(null).commit();
                    break;
                case "payer_select":
                    if (curItem == null) { break; }
                    query = String.format(res.getString(R.string.query_payer_select),
                            curItem.getName());
                    PayerSelectFragment payer_select = PayerSelectFragment.newInstance(query,
                            new int[0], true);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, payer_select)
                            .addToBackStack(null).commit();
                    break;
                case "payer_reselect":
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
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, payer_reselect)
                            .addToBackStack(null).commit();
                    break;
                case "yes_no_query":
                    if (curItem == null) { break; }
                    query = String.format(res.getString(R.string.query_yes_no), curItem.getQty(),
                            curItem.getName(), mSelected.size());
                    YesNoQueryFragment yes_no_query = YesNoQueryFragment.newInstance(query);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, yes_no_query)
                            .addToBackStack(null).commit();
                    break;
                case "number_query":
                    if (curItem == null) { break; }
                    query = String.format(res.getString(R.string.query_number), curItem.getName());
                    NumberQueryFragment number_query = NumberQueryFragment.newInstance(query,
                            mCurrentQty, mCurrentItem);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, number_query)
                            .addToBackStack(null).commit();
                    break;
                default:
                    break;
            }
        }
    }

    public void clearBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); i++) {
            fm.popBackStack();
        }
    }

    public int[] listToArray(ArrayList<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public ArrayList<Integer> arrayToList(int[] array) {
        ArrayList<Integer> list = new ArrayList<>();
        if (array != null) {
            for (int i : array) {
                list.add(i);
            }
        }
        return list;
    }

}
