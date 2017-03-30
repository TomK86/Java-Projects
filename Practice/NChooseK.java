/**
 * NChooseK.java
 * Recursive algorithm to find the solution to an n-choose-k problem.
 */

import java.io.*;
import java.util.*;

public class NChooseK {
  
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Insufficient arguments: NChooseK <n> <k>");
    } else {
      int n = Integer.parseInt(args[0]);
      int k = Integer.parseInt(args[1]);
      System.out.println(choose(n, k));
    }
  }

  private static int choose(int n, int k) {
    if (k == n) return 1;
    if (k == 1) return n;
    return choose(n-1, k) + choose(n-1, k-1);
  }

}