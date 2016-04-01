package com.tkelly.splitthebill;

import java.text.NumberFormat;
import java.text.ParseException;

/*
 * A class which stores information about a single payer
 *
 * Members:
 *  name - a String containing the name of this payer
 *  amt_owed - a double containing the amount this payer owes
 */
public class Payer {

    // Payer members
    private String name;
    private double amt_owed;

    // Payer constructors
    public Payer(String payer_name) {
        name = payer_name;
        amt_owed = 0d;
    }
    public Payer(String payer_name, double payer_amt_owed) {
        name = payer_name;
        amt_owed = payer_amt_owed;
    }

    // Get methods
    public String getName() { return name; }
    public double getAmtOwed() { return amt_owed; }
    public String getAmtOwedFormatted() {
        return NumberFormat.getCurrencyInstance().format(amt_owed);
    }

    // Set methods
    public void setName(String new_name) { name = new_name; }

    // Method to reset the amount the payer owes to zero
    public void clearAmtOwed() { amt_owed = 0d; }

    // Methods to add the given amount to the amount the payer owes
    public void updateAmtOwed(double amount) { amt_owed += amount; }
    public void updateAmtOwed(String formatted_amount) throws ParseException {
        amt_owed += NumberFormat.getCurrencyInstance().parse(formatted_amount).doubleValue();
    }

}
