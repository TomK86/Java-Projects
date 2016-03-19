/*
 * Minimum Cost of ropes
 *
 * There are given n ropes of different lengths, we need to connect
 * these ropes into one rope. The cost to connect two ropes is equal
 * to sum of their lengths. We need to connect the ropes with minimum
 * cost.
 *
 * Input:
 * The first line of input contains an integer T denoting the number of test cases.
 * The first line of each test case is N,N is the number of ropes.
 * The second line of each test case contains N input L[i],length of ropes.
 *
 * Output:
 * Print the minimum cost.
 *
 * Constraints:
 * 1 ≤ T ≤ 100
 * 1 ≤ N ≤ 500
 * 1 ≤ L[i] ≤ 500
 *
 * Example:
 *   Input:
 *   1
 *   4
 *   4 3 2 6
 *
 *   Output:
 *   29
 *
 * Explanation:
 * For example if we are given 4 ropes of lengths 4, 3, 2 and 6. We can connect
 * the ropes in following ways.
 *   1) First connect ropes of lengths 2 and 3. Now we have three ropes of lengths
 *      4, 6 and 5.
 *   2) Now connect ropes of lengths 4 and 5. Now we have two ropes of lengths 6 and 9.
 *   3) Finally connect the two ropes and all ropes have connected.
 *
 * Total cost for connecting all ropes is 5 + 9 + 15 = 29. This is the optimized cost
 * for connecting ropes. Other ways of connecting ropes would always have same or more
 * cost. For example, if we connect 4 and 6 first (we get three strings of 3, 2 and 10),
 * then connect 10 and 3 (we get two strings of 13 and 2). Finally we connect 13 and 2.
 * Total cost in this way is 10 + 13 + 15 = 38.
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Rope {
  /* Function to find the lowest cost of connecting ropes */
  public static int lowestCost(int[] L, int N) {
    int cost = 0;
    for (int n = N; n > 1; n--) {
      if (n == 2) { cost += L[0] + L[1]; }
      else {
        quickSort(L, 0, n-1);
        int[] new_L = Arrays.copyOfRange(L, 1, n);
        new_L[0] = L[0] + L[1];
        cost += new_L[0];
        L = new_L;
      }
    }
    return cost;
  }

  /* Quick Sort Algorithm */
  public static void quickSort(int[] A, int lo, int hi) {
    if (A == null || A.length < 2 || lo >= hi) { return; }
    int mid = lo + ((hi - lo) / 2);
    int pivot = A[mid];
    int i = lo;
    int j = hi;
    while (i <= j) {
      while (A[i] < pivot) { i++; }
      while (A[j] > pivot) { j--; }
      if (i <= j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
        i++;
        j--;
      }
    }
    if (lo < j) { quickSort(A, lo, j); }
    if (hi > i) { quickSort(A, i, hi); }
  }

  /* Main Function */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = 0;
    if (sc.hasNextInt()) { T = sc.nextInt(); }
    for (; T > 0; T--) {
      int N = 0;
      if (sc.hasNextInt()) { N = sc.nextInt(); }
      int[] L = new int[N];
      for (int i = 0; i < N; i++) {
        if (sc.hasNextInt()) { L[i] = sc.nextInt(); }
      }
      System.out.println(lowestCost(L, N));
    }
    sc.close();
  }
}