/*
 * Median of Two Sorted Arrays
 *
 * There are two sorted arrays arr1 and arr2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity
 * should be O(log (m+n)).
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class SortedArray {
  /* Function to find the median of two sorted arrays */
  public static double median(int[] arr1, int[] arr2) {
    double result = 0d;
    int idx1 = 0;
    int idx2 = 0;
    int length = arr1.length + arr2.length;
    int mid = length / 2;
    int[] array = new int[length];
    if (arr1.length + arr2.length == 0) {
      return -1d;
    }
    else if (arr1.length == 0) {
      int i = arr2.length / 2;
      if (arr2.length % 2 == 0) { result = (arr2[i] + arr2[i-1]) / 2d; }
      else { result = arr2[i]; }
      return result;
    }
    else if (arr2.length == 0) {
      int i = arr1.length / 2;
      if (arr1.length % 2 == 0) { result = (arr1[i] + arr1[i-1]) / 2d; }
      else { result = arr1[i]; }
      return result;
    }
    for (int i = 0; i < length; i++) {
      if (idx1 < arr1.length && idx2 < arr2.length) {
        if (arr1[idx1] <= arr2[idx2]) {
          array[i] = arr1[idx1];
          idx1++;
        }
        else {
          array[i] = arr2[idx2];
          idx2++;
        }
      }
      else if (idx1 < arr1.length) {
        array[i] = arr1[idx1];
        idx1++;
      }
      else if (idx2 < arr2.length) {
        array[i] = arr2[idx2];
        idx2++;
      }
      else { break; }
    }
    if (length % 2 == 0) { result = (array[mid] + array[mid-1]) / 2d; }
    else { result = array[mid]; }
    return result;
  }

  /* Main Function */
  public static void main(String[] args) {
    String[] arr1_str, arr2_str;
    int[] arr1, arr2;
    Scanner sc = new Scanner(System.in);
    sc.useDelimiter(System.getProperty("line.separator"));
    if (sc.hasNext()) {
      arr1_str = sc.next().split(" ");
      arr1 = new int[arr1_str.length];
      for (int i = 0; i < arr1_str.length; i++) {
        arr1[i] = Integer.parseInt(arr1_str[i]);
      }
    }
    if (sc.hasNext()) {
      arr2_str = sc.next().split(" ");
      arr2 = new int[arr2_str.length];
      for (int i = 0; i < arr2_str.length; i++) {
        arr2[i] = Integer.parseInt(arr2_str[i]);
      }
    }
    System.out.println(median(arr1, arr2));
    sc.close();
  }
}