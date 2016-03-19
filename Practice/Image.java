/*
 * Rotate a 2D array without using extra space
 *
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * You need to do this in place.
 * Note that if you end up using an additional array, you will only receive partial score.
 *
 * Example:
 *   If the array is:
 *   1 2 3 4 5 6 7 8 9
 *   Then the rotated array becomes: 
 *   7 4 1 8 5 2 9 6 3
 *
 * Input:
 * The first line contains an integer 'T' denoting the total number of test cases.
 * In each test cases, the first line contains an integer 'N' denoting the size of
 * the 2D square matrix.
 *
 * And in the second line, the elements of the matrix A[][], each separated by a space
 * in row major form.
 *
 * Output:
 * Print the elements of the rotated array row wise, each element separated by a space.
 *
 * Constraints:
 * 1 ≤ T ≤ 70
 * 1 ≤ N ≤ 10
 * 1 ≤ A [ i ][ j ] ≤ 100
 * 
 * Example:
 *   Input:
 *   1
 *   3
 *   1 2 3 4 5 6 7 8 9
 *
 *   Output:
 *   7 4 1 8 5 2 9 6 3
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Image {
  /* Function to rotate an image 90 degrees clockwise */
  public static void rotate(int[][] A, int N) {
    int i_max = (N / 2) + (N % 2);
    for (int i = 0; i < i_max; i++) {
      int j_max = N - i - 1;
      for (int j = i; j < j_max; j++) {
        int k = N - i - 1;
        int l = N - j - 1;
        int tmp = A[i][j];
        A[i][j] = A[l][i];
        A[l][i] = A[k][l];
        A[k][l] = A[j][k];
        A[j][k] = tmp;
      }
    }
  }

  /* Main Function */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = 0;
    if (sc.hasNextInt()) { T = sc.nextInt(); }
    for (; T > 0; T--) {
      int N = 0;
      if (sc.hasNextInt()) { N = sc.nextInt(); }
      int[][] A = new int[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (sc.hasNextInt()) { A[i][j] = sc.nextInt(); }
        }
      }
      rotate(A, N);
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          System.out.print(A[i][j] + " ");
        }
      }
      System.out.print("\n");
    }
    sc.close();
  }
}