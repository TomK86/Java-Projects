package com.tkelly.splitthebill;

import android.app.Application;
import java.util.ArrayList;
import java.util.HashMap;

public class MyApplication extends Application {
    // Global member variables
    private ArrayList<Item> items;
    private ArrayList<Payer> payers;
    private double tax;

    public MyApplication() {
        super();
        items = new ArrayList<>();
        payers = new ArrayList<>();
        tax = 0d;
    }

    // Get the item at the given index (returns null if not found)
    public Item getItem(int idx) {
        try {
            return items.get(idx);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    // Get the payer at the given index (returns null if not found)
    public Payer getPayer(int idx) {
        try {
            return payers.get(idx);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    // Get methods
    public ArrayList<Item> getItems() { return items; }
    public ArrayList<Payer> getPayers() { return payers; }
    public double getTax() { return tax; }
    public int getNumItems() { return items.size(); }
    public int getNumPayers() { return payers.size(); }

    // Clear methods
    public void clearAmtsOwed() { for (Payer payer : payers) payer.clearAmtOwed(); }
    public void clearAllPayments() { for (Item item : items) item.clearPayments(); }
    public void clearCompleted() { for (Item item : items) item.setCompleted(false); }

    // Check if arrays are empty
    public boolean payersIsEmpty() { return payers.isEmpty(); }
    public boolean itemsIsEmpty() { return items.isEmpty(); }

    // Set the sales tax percentage
    public void setTax(double new_tax) { tax = new_tax; }

    // Update each payer's amount owed according to the payments stored in items
    public void updateAmtsOwed() {
        clearAmtsOwed();
        for (Item item : items) {
            HashMap<Integer, Double> payments = item.getPayments();
            for (int i : payments.keySet()) {
                payers.get(i).updateAmtOwed(payments.get(i));
            }
        }
    }

    // Get the total cost before tax of all items currently in the list
//    public double subTotal() {
//        double total = 0d;
//        for (Item item : items) {
//            total += item.getCost() * item.getQty();
//        }
//        return total;
//    }
}
