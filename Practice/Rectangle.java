/**
 * Rectangle.java
 * Given a list of rectangles with perfectly vertical or horizontal sides, implement
 * a method that determines if the rectangles together form a "big rectangle", or a
 * rectangle with no gaps or overlap.
 */

import java.io.*;
import java.util.*;

public class Rectangle {

  // A rectangle is defined by the coordinates of the lower-left corner (x1, y1)
  // and the upper-right corner (x2, y2).  Thus x2 is always greater than x1,
  // and y2 is always greater than y1.  The length, width, and area are
  // derived from these two points.

  double x1, y1, x2, y2;
  double length, width, area;

  Rectangle(double x1, double y1, double x2, double y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.length = y2 - y1;
    this.width = x2 - x1;
    this.area = length * width;
  }

  public static boolean isBigRectangle(List<Rectangle> rectangles) {

    // Find the boundaries of all of the rectangles in the list:

    double minX1 = Double.MAX_VALUE;
    double minY1 = Double.MAX_VALUE;
    double maxX2 = Double.MIN_VALUE;
    double maxY2 = Double.MIN_VALUE;

    for (Rectangle r : rectangles) {
      if (r.x1 < minX1) { minX1 = r.x1; }
      if (r.y1 < minY1) { minY1 = r.y1; }
      if (r.x2 > maxX2) { maxX2 = r.x2; }
      if (r.y2 > maxY2) { maxY2 = r.y2; }
    }

    // Find all of the rectangles along the left, bottom, right, and top boundaries:

    List<Rectangle> left = new ArrayList<>();
    List<Rectangle> bottom = new ArrayList<>();
    List<Rectangle> right = new ArrayList<>();
    List<Rectangle> top = new ArrayList<>();

    for (Rectangle r : rectangles) {
      if (r.x1 == minX1) { left.add(r); }
      if (r.y1 == minY1) { bottom.add(r); }
      if (r.x2 == maxX2) { right.add(r); }
      if (r.y2 == maxY2) { top.add(r); }
    }

    // Add up the total length of rectangles along each boundary, then make sure
    // left = right and top = bottom:

    double leftLength = 0;
    double bottomWidth = 0;
    double rightLength = 0;
    double topWidth = 0;

    for (Rectangle r : left) { leftLength += r.length; }
    for (Rectangle r : bottom) { bottomWidth += r.width; }
    for (Rectangle r : right) { rightLength += r.length; }
    for (Rectangle r : top) { topWidth += r.width; }

    if (leftLength != rightLength || bottomWidth != topWidth) {
      return false;
    }

    // Check that each rectangle does not overlap any other rectangle:

    for (int i=0, len=rectangles.size(); i < len; i++) {
      Rectangle r1 = rectangles.get(i);
      for (int k = i+1; k < len; k++) {
        Rectangle r2 = rectangles.get(k);
        if (r1.x1 < r2.x2 && r1.x2 > r2.x1 &&
            r1.y1 < r2.y2 && r1.y2 > r2.y1) {
          return false;
        }
      }
    }

    // Calculate the bounded area from the boundary length & width:

    double boundedArea = leftLength * topWidth;
    double areaSum = 0;

    // Finally, add up the areas of each individual rectangle:

    for (Rectangle r : rectangles) {
      areaSum += r.area;
    }

    // If the area sum and the bounded area are equal, we have a valid big rectangle:

    return areaSum == boundedArea;
  }

}