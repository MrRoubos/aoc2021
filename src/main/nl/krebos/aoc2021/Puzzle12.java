package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Puzzle12 extends Puzzle {
  private Map<String, Cave> caves = new HashMap<>();
  
  public Puzzle12(String name, int day, int part, String file) {
    super(name, day, part, file);    
  }

  public long execute() {    
    long result = 0L;
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
    prl("Total caves: " + caves.size());
    printCaves();
    findPaths();
    
    return result;
  }

  private void findPaths() {
    boolean process = true;
    int counter = 0;
    Cave cave = caves.get("start");
    String path = cave.name + ",";
    cave.visited++;    
    while (process) {
      Cave nextCave = findNext(cave);
      path += nextCave.name + ",";
      if (nextCave.name.equals("end")) {
        counter++;
        resetVisited();
        prl ("Path : " + path);
        cave = caves.get("start");
        path = cave.name + ",";
        cave.visited++;        
        if (counter > 2) {
          process = false;          
        }
      }
      cave = nextCave;
    }    
  }

  private void resetVisited() {
    for (String str : caves.keySet()) {
      Cave cave = caves.get(str);
        cave.connectedCaves.forEach(it -> {
          caves.get(it).visited=0;
        });
    }   
  }
/*
 * of toch met een stack/ketting ?
 * start + A
 * start + b
 *  
 * A heeft 3 verbindingen (excl. start)
 * b heeft 3 verbindingen (excl. start)
 * start,A,c
 * start,A,b
 * start,A,end
 */
  private Cave findNext(Cave cave) {
    Iterator<String> it = cave.connectedCaves.iterator();
    Cave nextCave = null;
    while (it.hasNext()) {
      String next = it.next();
      nextCave = caves.get(next);
      if (! nextCave.big && nextCave.visited > 0) {
        prl("This cave is small and already visited: " + nextCave);
        // skip this small one
      } else {
        nextCave.visited++;
        break;
      }
    }
    return nextCave;
  }

  private void printCaves() {
    for (String key : caves.keySet()) {
      prl("cave: " + key + " " + caves.get(key));
    }    
  }

  private void readFile() {
    String input = "";
    try (var reader = new BufferedReader(new FileReader(this.getInputFile()))) {
      while ((input = reader.readLine()) != null) {
        for (int x =0; x < input.length(); x++) {
          String [] parts = input.split("-");
          createCave(parts[0]);
          createCave(parts[1]);
          connectCaves(parts[0],parts[1]);
        }
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }

  private void connectCaves(String c1, String c2) {
    Cave cave1 = caves.get(c1);
    Cave cave2 = caves.get(c2);
    cave1.connectedCaves.add(c2);
    cave2.connectedCaves.add(c1);    
  }

  private void createCave(String string) {
    Cave cave = caves.get(string);
    if (cave != null) {
      //
    } else {
      cave = new Cave();
      cave.name = string;
      if (string.equals(string.toUpperCase())) {
        cave.big = true;
      }
      caves.put(string, cave);
    }    
  }

  private long part2() {
    long result = 0L;
    return result;
  }
  
  public void printResult() {    
    prl(this + "; Result: " + this.getResult());
  } 
}
class Cave {
  String name;
  boolean big;
  int visited;
  Set<String> connectedCaves = new HashSet<>();
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("; name: " + name + "; big: " + big + "; visited: " + visited + "; connectedCaves: " + connectedCaves.size() + " ;");
    connectedCaves.forEach(it -> sb.append(it + ","));
    
    return sb.toString();
  }
  
}