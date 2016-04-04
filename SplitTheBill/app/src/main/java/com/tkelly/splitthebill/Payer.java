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

    // Set method
    public void setName(String new_name) { name = new_name; }

    // Clear method
    public void clearAmtOwed() { amt_owed = 0d; }

    // Method to add the given amount to the amount this payer owes
    public void updateAmtOwed(double amount) { amt_owed += amount; }

    // Method to get a string containing this payer's name and the amount they owe, if any
    public String getResult(double tax) {
        if (amt_owed > 0d) {
            return name + " owes " + NumberFormat.getCurrencyInstance().format(amt_owed * tax) +
                    "\n(" + NumberFormat.getCurrencyInstance().format(amt_owed) + " before tax)\n";
        } else { return ""; }
    }

    // Method to get a string containing a tip guide based on the amount this payer owes, if any
    public String getTipGuide() {
        if (amt_owed > 0d) {
            return "10% tip ... " + NumberFormat.getCurrencyInstance().format(amt_owed * 0.1d) +
                    "\n15% tip ... " + NumberFormat.getCurrencyInstance().format(amt_owed * 0.15d) +
                    "\n20% tip ... " + NumberFormat.getCurrencyInstance().format(amt_owed * 0.2d) + "\n\n";
        } else { return ""; }
    }

}
