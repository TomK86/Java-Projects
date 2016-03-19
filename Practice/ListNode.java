/*
 * Add Two Numbers
 *
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain
 * a single digit. Add the two numbers and return it as a linked list.
 *
 * Example:
 *   Input:
 *   (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *
 *   Output:
 *   7 -> 0 -> 8
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class ListNode {
  ListNode next;
  int val;
    
  ListNode(int x) {
    if (x < 10) { val = x; }
    else {
      val = x % 10;
      next = new ListNode(x / 10);
    }
  }
    
  public ListNode addTwoNumbers(ListNode l1, ListNode l2, int remainder) {
    int sum = l1.val + l2.val + remainder;
    if (sum >= 10) {
      remainder = 1;
      sum -= 10;
    }
    else { remainder = 0; }
    ListNode result = new ListNode(sum);
    if (l1.next != null && l2.next != null) {
      result.next = addTwoNumbers(l1.next, l2.next, remainder);
    }
    else if (l1.next != null) {
      result.next = addTwoNumbers(l1.next, new ListNode(0), remainder);
    }
    else if (l2.next != null) {
      result.next = addTwoNumbers(new ListNode(0), l2.next, remainder);
    }
    else if (remainder == 1) {
      result.next = new ListNode(1);
    }
    return result;
  }

  public String printable(ListNode l) {
    if (l == null) { return "NULL"; }
    else {
      String s = Integer.parseInt(l.val);
      while (l.next != null) {
        l = l.next;
        s += " -> " + l.val;
      }
      return s;
    }
  }

  public static void main(String[] args) {
    ListNode l1, l2;
    Scanner sc = new Scanner(System.in);
    if (sc.hasNextInt()) { l1 = new ListNode(sc.nextInt()); }
    if (sc.hasNextInt()) { l2 = new ListNode(sc.nextInt()); }
    sc.close();
    if (l1 == null || l2 == null) {
      System.out.println("Error: wrong input");
    }
    else {
      System.out.println("(" + printable(l1) + ") + (" + printable(l2) + ")");
      System.out.println("= " + printable(addTwoNumbers(l1, l2, 0)));
    }
  }
}