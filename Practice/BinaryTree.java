/**
 * BinaryTree.java
 * Practice with many different tree traversal algorithms: pre-order DFS, in-order
 * DFS, post-order DFS, level-order BFS, iterative-deepening DFS, A*, and Dijkstra.
 */

import java.io.*;
import java.util.*;

public class BinaryTree {

  public static final boolean recursive;
  
  public static void main(String[] args) {
    if (args.length == 0 || !args[0].equals("-r")) {
      recursive = false;
    } else {
      recursive = true;
    }
    Node tree = new Node("F", new Node("B", new Node("A", null, null), new Node("D", new Node("C", null, null), new Node("E", null, null))), new Node("G", null, new Node("I", new Node("H", null, null), null)));
    preorderDFS(tree);
    System.out.println();
    inorderDFS(tree);
    System.out.println();
    postorderDFS(tree);
    System.out.println();
    levelorderBFS(tree);
    System.out.println();
    dijkstra(tree, "G");
    System.out.println();
  }

  public static void preorderDFS(Node node) {
    if (recursive) {
      if (node == null) return;
      visit(node);
      preorderDFS(node.left);
      preorderDFS(node.right);
    } else {
      if (node == null) return;
      Stack<Node> s = new Stack<>();
      s.push(node);
      while (!s.empty()) {
        node = s.pop();
        visit(node);
        if (node.right != null) {
          s.push(node.right);
        }
        if (node.left != null) {
          s.push(node.left);
        }
      }
    }
  }

  public static void inorderDFS(Node node) {
    if (recursive) {
      if (node == null) return;
      inorderDFS(node.left);
      visit(node);
      inorderDFS(node.right);
    } else {
      Stack<Node> s = new Stack<>();
      while (!s.empty() || node != null) {
        if (node != null) {
          s.push(node);
          node = node.left;
        } else {
          node = s.pop();
          visit(node);
          node = node.right;
        }
      }
    }
  }

  public static void postorderDFS(Node node) {
    if (recursive) {
      if (node == null) return;
      postorderDFS(node.left);
      postorderDFS(node.right);
      visit(node);
    } else {
      Stack<Node> s = new Stack<>();
      Node peekNode, lastVisited = null;
      while (!s.empty() || node != null) {
        if (node != null) {
          s.push(node);
          node = node.left;
        } else {
          peekNode = s.peek();
          if (peekNode.right != null && peekNode.right != lastVisited) {
            node = peekNode.right;
          } else {
            visit(peekNode);
            lastVisited = s.pop();
          }
        }
      }
    }
  }

  public static void levelorderBFS(Node node) {
    LinkedList<Node> q = new LinkedList<>();
    if (node != null) q.add(node);
    while (q.size() > 0) {
      node = q.removeFirst();
      visit(node);
      if (node.left != null) q.add(node.left);
      if (node.right != null) q.add(node.right);
    }
  }

  public static Node idDFS(Node root, String target) {
    int depth = 0;
    Node found = null;
    while (found == null) {
      found = limitedDFS(root, depth, target);
      depth++;
    }
    return found;
  }

  public static Node limitedDFS(Node node, int depth, String target) {
    if (depth == 0 && node.data == target) return node;
    if (depth > 0) {
      Node found = null;
      if (node.left != null)
        found = limitedDFS(node.left, depth-1, target);
      if (node.right != null)
        found = limitedDFS(node.right, depth-1, target);
      if (found != null)
        return found;
    }
    return null;
  }

  public static void aStar(Node start, String target) {
    Map<Node,Node> cameFrom = new HashMap<>();
    Map<Node,Integer> gScore = new HashMap<>();
    Map<Node,Integer> fScore = new HashMap<>();
    Set<Node> closedSet = new HashSet<>();
    Set<Node> openSet = new HashSet<>();
    Node current;
    Integer min_f, g;
    openSet.add(start);
    gScore.put(start, 0);
    fScore.put(start, heuristic_cost_estimate(start, target));
    while (!openSet.isEmpty()) {
      current = null;
      min_f = Integer.MAX_VALUE;
      for (Node node : openSet) {
        Integer f = fScore.get(node);
        if (f < min_f) {
          min_f = f;
          current = node;
        }
      }
      openSet.remove(current);
      closedSet.add(current);
      if (!closedSet.contains(current.left)) {
        g = gScore.get(current) + dist_between(current, current.left);
        if (!openSet.contains(current.left)) {
          openSet.add(current.left);
        } else if (g < gScore.get(current.left)) {
          cameFrom.put(current.left, current);
          gScore.put(current.left, g);
          fScore.put(current.left, g + heuristic_cost_estimate(current.left, target));
        }
      }
    }
    List<Node> total_path = reconstruct_path(cameFrom, start);
    for (Node node : total_path) {
      System.out.print(node.data + " ");
    }
    System.out.println();
  }

  public static int heuristic_cost_estimate(Node node, String target) {
    // STUB
  }

  public static int dist_between(Node a, Node b) {
    // STUB
  }

  public static List<Node> reconstruct_path(Map<Node,Node> cameFrom, Node current) {
    List<Node> total_path = new ArrayList<>();
    total_path.add(current);
    while (cameFrom.containsKey(current)) {
      current = cameFrom.get(current);
      total_path.add(current);
    }
    return total_path;
  }

  public static void dijkstra(Node node, String target) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    if (node != null) pq.add(node);
    while (pq.size() > 0) {
      node = pq.poll();
      if (node.data == target) System.out.print("!");
      visit(node);
      if (node.right != null) pq.add(node.right);
      if (node.left != null) pq.add(node.left);
    }
  }

  public static void visit(Node node) {
    if (node != null) {
      node.visited = true;
      System.out.print(node.data + " ");
    }
  }

}

class Node implements Comparable<Node> {

  public String data;
  public Node left, right;
  public boolean visited;

  public Node(String data, Node left, Node right) {
    this.data = data;
    this.left = left;
    this.right = right;
    this.visited = false;
  }

  @Override
  public int compareTo(Node node) {
    return data.compareTo(node.data);
  }

}