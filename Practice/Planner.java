/*
 * The "Time Planner" Problem
 *
 * Implement a meeting planner that can schedule meetings between two persons at a time.
 * Time is represented by Unix format (also called Epoch) - a positive integer holding
 * the seconds since January 1st, 1970 at midnight.
 *
 * Input:
 *   dur - Meeting duration in seconds (a positive integer).
 *   timesA, timesB - Availability of each person, represented by an array of arrays. 
 *                    Each sub-array is a time span holding the start (first element)
 *                    and end (second element) times.
 *
 * Output:
 *   Array of two items - start and end times of the planned meeting, representing the
 *   earliest opportunity for the two persons to meet for a dur length meeting.   If no
 *   possible meeting can be scheduled, return an empty array instead.
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Planner {
  /* Function to find the earliest start and end times for a meeting */
  public int[] scheduleMeeting(int dur, int[][] timesA, int[][] timesB) {
    int idxA = 0, idxB = 0;
    while (idxA < timesA.length && idxB < timesB.length) {
      int start = Math.max(timesA[idxA][0], timesB[idxB][0]);
      int end = Math.min(timesA[idxA][1], timesB[idxB][1]);
      if (end - start >= dur) {
        return new int[]{start, start + dur}
      }
      else if (timesA[idxA][1] < timesB[idxB][1]) { idxA++; }
      else { idxB++; }
    }
    return new int[0];
  }

  /* Main Function */
  public static void main(String[] args) {
    int dur;
    int[][] timesA, timesB;

    Scanner sc = new Scanner(System.in);
    if (sc.hasNextLine()) {
      dur = Integer.parseInt(sc.nextLine());
    }
    if (sc.hasNextLine()) {
      String[] str = sc.nextLine().split(" ");
      timesA = new int[str.length / 2][2];
      for (int i = 0; i < str.length / 2; i += 2) {
        timesA[i][0] = Integer.parseInt(str[2*i]);
        timesA[i][1] = Integer.parseInt(str[2*i+1]);
      }
    }
    if (sc.hasNextLine()) {
      String[] str = sc.nextLine().split(" ");
      timesB = new int[str.length / 2][2];
      for (int i = 0; i < str.length / 2; i += 2) {
        timesB[i][0] = Integer.parseInt(str[2*i]);
        timesB[i][1] = Integer.parseInt(str[2*i+1]);
      }
    }
    sc.close();
    
    if (dur != null && timesA != null && timesB != null) {
      int[] meetingTimes = scheduleMeeting(dur, timesA, timesB);
      if (meetingTimes.length == 2) {
        System.out.println(meetingTimes[0] + " - " + meetingTimes[1]);
      }
      else {
        System.out.println("No compatible meeting times found.");
      }
    }
    else {
      System.out.println("Error: invalid input");
    }
  }
}
