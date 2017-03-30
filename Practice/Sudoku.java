/**
 * Sudoku.java
 * Given a completed sudoku board, check whether it is a valid solution or not.
 */

import java.io.*;
import java.util.*;

public class Sudoku {

  public static void main(String[] args) {
    int[][] valid_board = new int[][]{{1, 2, 3, 4},
                                      {3, 4, 1, 2},
                                      {2, 3, 4, 1},
                                      {4, 1, 2, 3}};
    int[][] invalid_board = new int[][]{{1, 2, 3, 4},
                                        {3, 4, 1, 2},
                                        {2, 3, 4, 1},
                                        {3, 1, 2, 4}};
    boolean test1 = isValid(valid_board);
    boolean test2 = !isValid(invalid_board);
    System.out.println((test1 && test2) ? "Success!" : "Fail!");
  }

  private static boolean isValid(int[][] board) {
    Set<Integer> set_row = new HashSet<>();
    Set<Integer> set_col = new HashSet<>();
    int n = board[0].length;
    int m = (int) Math.sqrt(n);

    // Check that each row & column is valid:

    for (int i = 0; i < n; i++) {
      for (int k = 0; k < n; k++) {
        if (!set_row.add(board[i][k])) return false;
        if (!set_col.add(board[k][i])) return false;
      }
      set_row.clear();
      set_col.clear();
    }

    Set<Integer> set = new HashSet<>(); // could re-use set_row or set_col here

    // Check that each sub-square is valid:

    for (int x = 0; x < n; x += m) {
      for (int y = 0; y < n; y += m) {
        for (int i = x; i < x+m; i++) {
          for (int k = y; k < y+m; k++) {
            if (!set.add(board[i][k])) return false;
          }
        }
        set.clear();
      }
    }

    return true;
  }

}