/*
 * RestaurantBill
 *
 * A Java project which takes in a list of line items on a bill and
 * calculates the amount owed by each payer.
 */

import java.util.*;
import java.lang.*;
import java.io.*;

/*
 * The top-level class, which stores Payer and Item objects and uses
 * this information to split the bill
 *
 * Members:
 *  P - an array of Payer objects representing all of the people who owe money
 *  I - an array of Item objects representing all of the line items on the bill
 *  tax - a double (>= 1) representing the local sales tax percentage
 */
public class RestaurantBill {
  // RestaurantBill members
  private HashMap<String, Payer> P;
  private ArrayList<Item> I;
  private double tax;

  // RestaurantBill default constructor
  RestaurantBill() {
    P = new HashMap<String, Payer>();
    I = new ArrayList<Item>();
    tax = 0d;
  }

  // RestaurantBill constructor with arguments
  RestaurantBill(HashMap<String, Payer> payers, ArrayList<Item> items, double tax_percentage) {
    P = new HashMap<String, Payer>(payers);
    I = new ArrayList<Item>(items);
    tax = tax_percentage;
  }

  // Method to get the sub-total, or total before tax, of all items
  public double subTotal() {
    int qty;
    double cost, total = 0d;
    for (int i = 0; i < I.size(); i++) {
      cost = I[i].getCost();
      qty = I[i].getQty();
      total += cost * qty;
    }
    return total;
  }

  // Method to add a payer to the list of payers (returns the added Payer)
  public void addPayer(String name) { P.put(name, new Payer()); }

  // Method to add an item to the list of items
  public void addItem(Item item) { I.add(item); }

  // Method to empty the list of line items
  public void clearItems() { I = new ArrayList<Item>(); }

  // Method to empty the list of payers
  public void clearPayers() { P = new HashMap<String, Payer>(); }

  // Method to reset the amount owed by each payer to zero
  public void clearAmtsOwed() {
    for (Payer p : P.values()) { p.clearAmtOwed(); }
  }

  // Method to ask the user for input about the local sales tax
  public void getTax() {
    double response;
    Scanner input = new Scanner(System.in);
    System.out.print("What is the local sales tax? ");
    try { response = Double.parseDouble(input.nextLine()); }
    catch (NumberFormatException e) { e.printStackTrace(); }
    input.close();

    if (response < 0d) {
      System.out.println("Error: tax percentage cannot be negative");
      getTax();
      return;
    }
    else if (response > 100d) {
      System.out.println("Error: tax percentage cannot exceed 100%");
      getTax();
      return;
    }
    else if (response < 1d) { tax = response + 1d; }
    else { tax = (response / 100d) + 1d; }
  }

  // Method to ask the user for input about line items on the bill
  public void getItems() {
    String name, response;
    double cost;
    int qty, idx = 1;

    clearItems();
    Scanner input = new Scanner(System.in);

    System.out.print("What is the name of the first line item? ");
    name = input.nextLine();
    if (name.isEmpty()) { name = "Line Item #" + idx; }

    System.out.print("How many " + name + "(s) are on the bill? ");
    try {
      qty = Integer.parseInt(input.nextLine());
    } catch (NumberFormatException e) { e.printStackTrace(); }
    if (qty == null || qty < 1) { qty = 1; }

    System.out.print("What is the cost of each " + name + " (before tax)? $");
    try {
      cost = Double.parseDouble(input.nextLine());
    } catch (NumberFormatException e) { e.printStackTrace(); }
    if (cost == null) { cost = 0d; }

    addItem(new Item(cost, qty, name));
    idx++;

    System.out.print("Are there any more line items to add (y/n)? ");
    response = input.nextLine();

    while (response == "y" || response == "Y") {
      System.out.print("What is the name of the next line item? ");
      name = input.nextLine();
      if (name.isEmpty()) { name = "Line Item #" + idx; }

      System.out.print("How many " + name + "(s) are on the bill? ");
      try {
        qty = Integer.parseInt(input.nextLine());
      } catch (NumberFormatException e) { e.printStackTrace(); }
      if (qty == null || qty < 1) { qty = 1; }

      System.out.print("What is the cost of each " + name + " (before tax)? $");
      try {
        cost = Double.parseDouble(input.nextLine());
      } catch (NumberFormatException e) { e.printStackTrace(); }
      if (cost == null) { cost = 0d; }

      addItem(new Item(cost, qty, name));
      idx++;

      System.out.print("Are there any more line items to add (y/n)? ");
      response = input.nextLine();
    }

    input.close();
  }

  // Method to ask the user for input about the payers responsible for the bill
  public void getPayers() {
    String name, response;
    int idx = 1;

    clearPayers();
    Scanner input = new Scanner(System.in);

    System.out.print("What is the first payer's name? ");
    name = input.nextLine();
    if (name.isEmpty()) { name = "Payer #" + idx; }
    addPayer(name);
    idx++;

    System.out.print("Are there any more payers to add (y/n)? ");
    response = input.nextLine();

    while (response == "y" || response == "Y") {
      System.out.print("What is the next payer's name? ");
      name = input.nextLine();
      if (name.isEmpty()) { name = "Payer #" + idx; }
      addPayer(name);
      idx++;

      System.out.print("Are there any more payers to add (y/n)? ");
      response = input.nextLine();
    }

    input.close();
  }

  // Method to ask the user for input about what item(s) each payer had
  public void getAmtsOwed(boolean keep_current_payers) {
    String item, name;
    int idx, qty, q;
    double cost;
    Payer p;

    if (keep_current_payers) {
      clearAmtsOwed();
      idx = P.size() + 1;
    }
    else {
      clearPayers();
      idx = 1;
    }

    Scanner input = new Scanner(System.in);

    for (int i = 0; i < I.size(); i++) {
      item = I[i].getName();
      qty = I[i].getQty();
      cost = I[i].getCost();

      System.out.print("Who had " + item + "? ");
      name = input.nextLine();
      if (name.isEmpty()) { name = "Payer #" + idx; }
      if (P.get(name) == null) { addPayer(name); }
      p = P.get(name);
      idx++;

      while (qty > 1) {
        System.out.print("How many? ");
        try { q = Integer.parseInt(input.nextLine()); }
        catch (NumberFormatException e) { e.printStackTrace(); }
        if (q < 1) {
          System.out.println("Error: item quantity cannot be less than 1");
        }
        else if (q > qty) {
          System.out.println("Error: item quantity cannot exceed what is available (" + qty + ")");
        }
        else {
          p.updateAmtOwed(cost * q);
          qty -= q;
          if (qty > 0) {
            System.out.print("Who else had " + item + "? ");
            name = input.nextLine();
            if (P.get(name) == null) { addPayer(name); }
            p = P.get(name);
          }
        }
      }

      if (qty == 1) { p.updateAmtOwed(cost); }
    }

    input.close();
  }

  // Method to split a restaurant bill and print the amount each payer owes
  public void split(boolean start_over) {
    double total, amt_owed;

    if (start_over) {
      getTax();
      getItems();
      getPayers();
      getAmtsOwed(false);
    }
    else {
      if (tax == 0d) { getTax(); }
      if (I.size() == 0) { getItems(); }
      if (P.size() == 0) { getPayers(); }
      getAmtsOwed(true);
    }

    total = subTotal();
    System.out.println("Subtotal: $" + total);
    System.out.println("Tax: %" + (tax * 100));
    total *= tax;
    System.out.println("Grand Total: $" + total);
    System.out.println("=" * 40);

    for (String name : P.keySet()) {
      amt_owed = P.get(name).getAmtOwed();
      System.out.println(name + " owes $" + amt_owed + " before tax");
      amt_owed *= tax;
      System.out.println(name + " owes $" + amt_owed + " after tax");
    }
  }
}


/*
 * A class which stores information about a single payer
 *
 * Members:
 *  amt_owed - a double containing the amount the payer owes
 */
class Payer {
  // Payer members
  private double amt_owed;

  // Payer default constructor
  Payer() {
    amt_owed = 0d;
  }

  // Method to get the amount the payer owes
  public double getAmtOwed() { return amt_owed; }

  // Method to reset the amount the payer owes to zero
  public void clearAmtOwed() { amt_owed = 0d; }

  // Method to update (add to) the amount the payer owes
  public void updateAmtOwed(double amount) { amt_owed += amount; }
}


/*
 * A class which stores information about a single line item on the bill
 *
 * Members:
 *  qty - an integer (>0) containing the quanitity of the item
 *  cost - a double containing the cost for each item
 *  name - a string containing the name of the item
 */
class Item {
  // Item members
  private int qty;
  private double cost;
  private String name;

  // Item constructor for a single unnamed item
  Item(double item_cost) {
    qty = 1;
    cost = item_cost;
    name = "";
  }

  // Item constructor for a single named item
  Item(double item_cost, String item_name) {
    qty = 1;
    cost = item_cost;
    name = item_name;
  }

  // Item constructor for duplicate unnamed items
  Item(double cost_per_item, int quantity) {
    qty = quantity;
    cost = cost_per_item;
    name = "";
  }

  // Item constructor for duplicate named items
  Item(double cost_per_item, int quantity, String item_name) {
    qty = quantity;
    cost = cost_per_item;
    name = item_name;
  }

  // Get methods
  public int getQty() { return qty; }
  public double getCost() { return cost; }
  public String getName() { return name; }
}

/* Main Function */
public static void main(String[] args) {
  RestaurantBill bill = new RestaurantBill();
  bill.split(true);
}
