/*
 * Number of ways
 *
 * Given a tile of size 1 x 4, how many ways you can construct a grid of size N x 4.
 *
 * Input:
 * The first line of input contains an integer T denoting the number of test cases.
 * The first line of each test case is N.
 *
 * Output:
 * Print number of possible ways.
 *
 * Constraints:
 * 1 ≤ T ≤ 50
 * 1 ≤ N ≤ 80
 *
 * Example:
 *   Input:
 *   3
 *   1
 *   4
 *   5
 *
 *   Output:
 *   1
 *   2
 *   3
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Tile {
  /*
   * Function to find the number of ways that
   * 1x4 tiles can be arranged in an Nx4 grid
   */
  public static long countTheWays(int N) {
    if (N <= 0) { return 0; }
    else if (N < 4) { return 1; }
    long[] result = new long[N];
    result[0] = 1;
    result[1] = 1;
    result[2] = 1;
    result[3] = 2;
    for (int i = 4; i < N; i++) {
      result[i] = result[i-1] + result[i-4];
    }
    return result[N-1];
  }

  /* Main Function */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = 0;
    if (sc.hasNextInt()) { T = sc.nextInt(); }
    for (; T > 0; T--) {
      if (sc.hasNextInt()) {
        int N = sc.nextInt();
        System.out.println(countTheWays(N));
      }
    }
    sc.close();
  }
}