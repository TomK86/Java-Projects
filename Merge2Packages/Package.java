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

/* Package Class */
public class Package {
  private static int N;

  /*
   * Function to find two items in arr whose sum equals limit
   * Calls binarySearch within an O(n) for-loop
   * Time complexity: O(n log n)
   * Space complexity: O(n)
   */
  public static int[] findItems (double limit, double[] arr) {
    double[] sorted_arr = new double[arr.length];
    System.arraycopy(arr, 0, sorted_arr, 0, arr.length);
    heapSort(sorted_arr);
    for (int i = 0; i < sorted_arr.length; i++) {
      if (sorted_arr[i] * 2d > limit) { break; }
      double result = binarySearch(sorted_arr, limit - sorted_arr[i]);
      if (result != -1d) {
        i = arr.indexOf(sorted_arr[i]);
        int j = arr.indexOf(result);
        return new int[]{i, j};
      }
    }
    return new int[]{-1};
  }
  
  /*
   * Binary Search Algorithm
   * Time Complexity: O(log n)
   * Space complexity: O(n)
   */
  public static double binarySearch(double[] arr, double target) {
    if (arr == null) { return -1d; }
    if (arr.length < 3) {
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) { return arr[i]; }
      }
      return -1d;
    }
    int i = arr.length / 2;
    if (arr[i] < target) {
      return binarySearch(Arrays.copyOfRange(arr, i+1, arr.length-1), target);
    }
    else if (arr[i] > target) {
      return binarySearch(Arrays.copyOfRange(arr, 0, i), target);
    }
    else {
      return arr[i];
    }
  }

  /* 
   * Heap Sort Algorithm
   * Time complexity: O(n log n)
   * Space complexity: O(1)
   */
  public static void heapSort(double[] arr) {
    heapify(arr);
    for (int i = N; i > 0; i--) {
      swap(arr,0, i);
      N--;
      maxHeap(arr, 0);
    }
  }

  /* Function to build a heap */
  public static void heapify(double[] arr) {
    N = arr.length - 1;
    for (int i = N / 2; i >= 0; i--) { maxHeap(arr, i); }
  }

  /* Function to swap the largest element in a heap */
  public static void maxHeap(double[] arr, int i) {
    int left = 2 * i;
    int right = left + 1;
    int max = i;
    if (left <= N && arr[left] > arr[i]) { max = left; }
    if (right <= N && arr[right] > arr[max]) { max = right; }
    if (max != i) {
      swap(arr, i, max);
      maxHeap(arr, max);
    }
  }

  /* Function to swap two numbers in an array */
  public static void swap(double[] arr, int i, int j) {
    double tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  /* Main Function */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    double limit = 0d;
    ArrayList<Double> list = new ArrayList<Double>();
    while (sc.hasNextDouble()) {
      if (limit == 0d) { limit = sc.nextDouble(); }
      else { list.add(sc.nextDouble()); }
    }
    double[] arr = new double[list.size()];
    arr = list.toArray(arr);
    int[] result = findItems(limit, arr);
    if (result[0] == -1) {
      System.out.println("No item pairs were found with total weight equal to limit.");
    }
    else {
      int i = arrresult[0];
      int j = result[1];
      System.out.println("Index of item 1: " + Integer.toString(i));
      System.out.println("Weight of item 1: " + Double.toString(arr[i]));
      System.out.println("Index of item 2: " + Integer.toString(j));
      System.out.println("Weight of item 2: " + Double.toString(arr[j]));
    }
  }
}

