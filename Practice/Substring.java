/*
 * Longest Substring Without Repeating Characters
 *
 * Given a string, find the length of the longest substring without
 * repeating characters. For example, the longest substring without
 * repeating letters for "abcabcbb" is "abc", which the length is 3.
 * For "bbbbb" the longest substring is "b", with the length of 1.
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Substring {
  /* Function to find the length of the longest non-repeating substring */
  public static int longestNonRepeating(String s) {
    int length = 0;
    
    for (int i = 0; i < s.length(); i++) {
      String substring = "" + s.charAt(i);
      for (int j = i+1; j < s.length(); j++) {
        char letter = s.charAt(j);
        if (substring.indexOf(letter) >= 0) { break; }
        else { substring += letter; }
      }
      if (substring.length() > length) {
        length = substring.length();
      }
    }
    
    return length;
  }

  /* Main Function */
  public static void main(String[] args) {
    String s;
    
    Scanner sc = new Scanner(System.in);
    if (sc.hasNext()) { s = sc.next(); }
    sc.close();
    
    if (s == null) { System.out.println("Error: invalid input"); }
    else { System.out.println(longestNonRepeating(s)); }
  }
}
