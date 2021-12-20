package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Puzzle15a extends Puzzle {
  int maxX;
  int maxY;
  int [][]map;
  Node node;
  
  public Puzzle15a(String name, int day, int part, String file) {
    super(name, day, part, file);    
  }

  public long execute() {    
    long result = 0L;
    map = new int[maxX][maxY];
    Stopwatch stopwatch = new Stopwatch();
    
    if (this.getPart() == 1) {
      result = part1();
      stopwatch.split("end of part1");
    } else {
      result = part2();
      stopwatch.split("end of part2");
    }
    
    this.setResult(result);
    printResult(); 
    return result;
  }

  private long part1() {
    long result = 0L;   
    readFile();
    initNodes();
    process();
    return result;
  }

  private void initNodes() {
    Node[][] nodeMap = new Node[maxX][maxY];
    for (int y=0;y<maxY;y++) {
      for (int x=0;x<maxX;x++) {
        nodeMap[x][y]  = new Node(x,y,map[x][y]);        
      }
    }
    
    for (int y=0;y<maxY;y++) {
      for (int x=0;x<maxX;x++) {
        Node node = nodeMap[x][y];
        if (x < maxX-1) {
          node.connect(nodeMap[x+1][y]);
        }
        if (y < maxY-1) {
          node.connect(nodeMap[x][y+1]);
        }    
        if (y > 0 ) {
          node.connect(nodeMap[x][y-1]);
        }    
        if (x > 0 ) {
          node.connect(nodeMap[x-1][y]);
        }        
      }
    }    
    for (int y=0;y<maxY;y++) {
      for (int x=0;x<maxX;x++) {
        prl("Node -> " + nodeMap[x][y]);
        for (Node neighbor : nodeMap[x][y].neighbors) {
          prl("Neighbors -> " + neighbor);
        }
      }
      node = nodeMap[0][0];
      //if (y>2) break;
    }    
  }

  /**
   * https://www.redblobgames.com/pathfinding/a-star/introduction.html
   * https://www.baeldung.com/java-breadth-first-search
   * Queue -> https://www.javatpoint.com/java-queue 
   * Set   -> https://www.javatpoint.com/set-in-java
   * 
   * for path finding rename reached to cameFrom
   */
  private void process() {
    Queue<Node> frontier = new ArrayDeque<>();
    //https://howtodoinjava.com/java/collections/java-priorityqueue/
    //Queue<Node> frontier = new PriorityQueue<>();
    
    Set<Node> reached = new HashSet<>();
    Map<Node,Node> cameFrom = new HashMap<>();
    Map<Node,Node> costSoFar = new HashMap<>();
    Node start = node;
    Node goal = null;
    frontier.add(node);
    cameFrom.put(node, null);
    prl("=============================");
    while (! frontier.isEmpty()) {
      Node current = frontier.remove();
      prl("Visited node: " + current);
      for (Node next : current.neighbors) {        
        if ((cameFrom.get((next)) == null)) {
          frontier.add(next);
          cameFrom.put(next, current);
          goal = next;
          prl("Neighbor: " + next + ";frontier: " + frontier.size() + "; cameFrom: " + cameFrom.size());          
        }
      }      
    }
    prl("Goal = " + goal);
    Node current = goal;
    List<Node> path = new ArrayList<>();
    while (! current.equals(start)) {
      path.add(current);
      current = cameFrom.get(current);      
    }
    prl ("==== path =======");
    for (Node node : path) {
      prl("Node : " + node);
    }
  }


  private long part2() {
    long result = 0L;   
    readFile();
    return result;
  }
  
  private void readFile() {
    String input = "";
        
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      int y=0;
      while ((input = reader.readLine()) != null) {
        for (int x=0;x<input.length();x++) {
          map[x][y]=Integer.parseInt(input.substring(x,x+1));
        }
        y++;
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void printResult() {    
    prl(this + "; Result: " + this.getResult());
  } 
}
class Node {
  int x;
  int y;
  int cost;
  Set <Node>neighbors;
  
   public Node (int x, int y, int cost) {
     this.x = x;
     this.y = y;
     this.cost = cost;
     this.neighbors = new HashSet<>();         
   }
     
  public void connect(Node node) {
     if (this == node) throw new IllegalArgumentException("Can't connect node to itself");
     this.neighbors.add(node);
     node.neighbors.add(this);
  } 
   
   public String toString() {
     return "Node: x="+x+";y="+y+";cost="+cost;
   }
}