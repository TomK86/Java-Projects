package com.tkelly.splitthebill;

/*
 * A class which stores information about a single line item on the bill
 *
 * Members:
 *  qty - an integer (>0) containing the quantity of the item
 *  cost - a double containing the cost for each item
 *  name - a string containing the name of the item
 */
public class Item {

    // Item members
    private int qty;
    private double cost;
    private String name;
    private boolean completed;

    // Item constructor
    public Item(double cost_per_item, int quantity, String item_name) {
        qty = quantity;
        cost = cost_per_item;
        name = item_name;
        completed = false;
    }

    // Get methods
    public int getQty() { return qty; }
    public double getCost() { return cost; }
    public String getName() { return name; }
    public boolean isCompleted() { return completed; }

    // Set methods
    public void setQty(int new_qty) { qty = new_qty; }
    public void setCost(double new_cost) { cost = new_cost; }
    public void setName(String new_name) { name = new_name; }
    public void setCompleted(boolean is_completed) { completed = is_completed; }

}
