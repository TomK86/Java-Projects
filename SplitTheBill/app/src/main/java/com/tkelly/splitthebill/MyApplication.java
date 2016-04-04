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

    // Get the first item with the given name (returns null if not found)
    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) { return item; }
        }
        return null;
    }

    // Get the payer at the given index (returns null if not found)
    public Payer getPayer(int idx) {
        try {
            return payers.get(idx);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    // Get the first payer with the given name (returns null if not found)
    public Payer getPayer(String name) {
        for (Payer payer : payers) {
            if (payer.getName().equals(name)) { return payer; }
        }
        return null;
    }

    // Get methods
    public ArrayList<Item> getItems() { return items; }
    public ArrayList<Payer> getPayers() { return payers; }
    public double getTax() { return tax; }
    public int getNumItems() { return items.size(); }
    public int getNumPayers() { return payers.size(); }
    public int[] getAllPayerIndices() {
        int[] indices = new int[payers.size()];
        for (int i = 0; i < payers.size(); i++) {
            indices[i] = i;
        }
        return indices;
    }

    // Add methods
    public void addItem(Item item) { items.add(item); }
    public void addItem(double cost, int qty, String name) { items.add(new Item(cost, qty, name)); }
    public void addPayer(Payer payer) { payers.add(payer); }
    public void addPayer(String name) { payers.add(new Payer(name)); }
    public void addPayer(String name, double amt_owed) { payers.add(new Payer(name, amt_owed)); }

    // Remove methods
    public void removeItem(Item item) { items.remove(item); }
    public void removeItem(int idx) { items.remove(idx); }
    public void removePayer(Payer payer) { payers.remove(payer); }
    public void removePayer(int idx) { payers.remove(idx); }

    // Clear methods
    public void clearItems() { items = new ArrayList<>(); }
    public void clearCompleted() { for (Item i : items) i.setCompleted(false); }
    public void clearPayers() { payers = new ArrayList<>(); }
    public void clearAmtsOwed() {
        for (Payer payer : payers) {
            payer.clearAmtOwed();
        }
    }
    public void clearAllPayments() {
        for (Item item : items) {
            item.clearPayments();
        }
    }

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
    public double subTotal() {
        double total = 0d;
        for (Item item : items) {
            total += item.getCost() * item.getQty();
        }
        return total;
    }
}
