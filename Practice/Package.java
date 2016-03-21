/*
 * Merging 2 Packages
 * 
 * Given a package with a weight limit and an array arr of item weights, how
 * can you most efficiently find two items with sum of weights that equals the
 * weight limit?
 * 
 * Your function should return 2 such indices of item weights or -1 if such pair
 * doesn't exist.  What is the runtime and space complexity of your solution?
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Package {
  private static int N;

  /*
   * Function to find indices of two items in arr whose sum equals limit
   * Time complexity: O(n)
   * Space complexity: O(m), where m is the number of unique weights in the array
   */
  public static int[] findItems(double limit, double[] arr) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < arr.length; i++) {
      int j = map.findKey(limit - arr[i]);
      if (j != null) { return new int[]{i, j}; }
      else { map.insert(arr[i], i); }
    }
    return new int[]{-1};
  }

  /* Main Function */
  public static void main(String[] args) {
    double limit;
    double[] arr;
    String[] str;

    Scanner input = new Scanner(System.in);
    System.out.print("Enter weight limit: ");
    limit = Double.parseDouble(input.nextLine());
    System.out.println("Enter item weights:");
    str = input.nextLine().split(" ");
    arr = new double[str.length];
    for (int i = 0; i < str.length; i++) {
      arr[i] = Double.parseDouble(str[i]);
    }
    input.close();

    if (limit != null && arr != null) {
      int[] result = findItems(limit, arr);
      if (result[0] == -1) {
        System.out.println("No item pairs were found with total weight equal to limit.");
      }
      else {
        int i = result[0];
        int j = result[1];
        System.out.println("Index of item 1: " + i);
        System.out.println("Weight of item 1: " + arr[i]);
        System.out.println("Index of item 2: " + j);
        System.out.println("Weight of item 2: " + arr[j]);
      }
    }
    else {
      System.out.println("Error: invalid input");
    }
  }
}

