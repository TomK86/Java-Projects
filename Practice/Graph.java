/**
 * Graph.java
 * Practice with depth-first search (both iterative and recursive) and breadth-first search.
 */

import java.io.*;
import java.util.*;

public class Graph {
  
  public static void main(String[] args) {
    Node graph = new Node(new ArrayList<Node>(), 0);
  }

  public static Node dfs(Node start, Node target) {
    Node top;
    Stack<Node> s = new Stack<>();
    s.push(start);
    while (!s.empty()) {
      top = s.top();
      s.pop();
      if (top.isNotVisited()) {
        if (top == target) return top;
        top.visit();
        for (Node neighbor : top.getNeighbors()) {
          if (neighbor.isNotVisited()) s.push(neighbor);
        }
      }
    }
    return null;
  }

  public static Node dfsRec(Node current, Node target) {
    current.visit();
    if (current == target) return current;
    Node result;
    for (Node neighbor : current.getNeighbors()) {
      if (neighbor.isNotVisited()) result = dfsRec(neighbor, target);
      if (result != null) return result;
    }
    return null;
  }

  public static Node bfs(Node start, Node target) {
    Node top;
    LinkedList<Node> q = new LinkedList<>();
    q.add(start);
    start.visit();
    while (q.size() > 0) {
      top = q.removeFirst();
      if (top == target) return top;
      for (Node neighbor : top.getNeighbors()) {
        if (neighbor.isNotVisited()) {
          neighbor.visit();
          q.add(neighbor);
        }
      }
    }
    return null;
  }

}

class Node {
  private List<Node> neighbors;
  private int data;
  private boolean visited;

  public Node(List<Node> neighbors, int data) {
    this.neighbors = neighbors;
    this.data = data;
    this.visited = false;
  }

  public Node cost(Node node) {
    if (neighbors.contains(node)) return node;
    else return null;
  }

  public void visit() {
    visited = true;
  }

  public boolean isNotVisited() {
    return !visited;
  }

  public List<Node> getNeighbors() {
    return neighbors;
  }
}