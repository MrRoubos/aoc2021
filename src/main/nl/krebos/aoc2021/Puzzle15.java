package nl.krebos.aoc2021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Puzzle15 extends Puzzle {
  int maxX;
  int maxY;
  int [][]map;
  int x=0;
  int y=0;
  int maxSteps=0;
  Stack<Cell15> stack = new Stack<>();
  
  public Puzzle15(String name, int day, int part, String file) {
    super(name, day, part, file);    
  }

  public long execute() {    
    long result = 0L;
    map = new int[maxX][maxY];
    maxSteps = maxX-1;
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
    process();
    return result;
  }

  private void process() {

    // start at 0,0 end you have reached the end if you
    // are at the right low corner
    int cx=0;
    int cy=0;

    String direction=null;
    String prevDirection = "";
    int steps = 1;
    while (x != maxX-1 && y != maxY) {      
      direction = findDirection2(x,y, prevDirection);
      findNext(direction);
      prevDirection = direction;
    }
//    Cell15 c = new Cell15(x,y, map[x][y]);
//    stack.add(c);

    stack.forEach(it -> {
      prl("" + it);
    });
    int count=0;
    Iterator it = stack.iterator();
    while (it.hasNext()) {
      count += ((Cell15) it.next()).value;
    }
    prl ("count: " + count);
  }

  private void findNext(String direction) {
    // check surrounding values
    if (direction.equals("r")) {
      x++;
      if (x<maxX-1) {
        Cell15 c = new Cell15(x,y, map[x][y]);
        stack.add(c);        
      }
    } else if (direction.equals("l")) {
      x--;
      if (x > 0) {
        Cell15 c = new Cell15(x,y, map[x][y]);
        stack.add(c);        
      }      
    } else if (direction.equals("d")) {
      y++;
      if (y < maxY-1) {
        Cell15 c = new Cell15(x,y, map[x][y]);
        stack.add(c);        
      }
  
    }   
  }

  private String findDirection2(int x2, int y2, String direction) {
    prl("- findDirection2, x=" + x2 + ";y="+y2 + "; prevDirection: " + direction);
    int rr=0;
    int ll=0;
    int dd=0;
    int uu=0;
    for (int i=1; i<=maxSteps;i++) {
      if (x2+i < maxX-1) {
        pr("x="+(x2+i)+";");
        rr += map[x2+i][y];
      }
      if (x2-i > 0) {
        pr("x="+(x2-i)+";");
        ll += map[x2-i][y];
      }      
      
      if (y2+i < maxY-1) {
        pr("y="+(y2+i)+";");
        dd += map[x][y2+i];
      }     
     if (dd < rr || dd > rr) break;        
    }
    prl("rr="+rr+";dd=" +dd+";uu="+uu+";ll="+ll);
    if (dd < rr)  {
      return "d";
    } else {      
      return "r";
    }
  
  }
  
  
  private String findDirection(int x2, int y2, int x3, int y3, int steps) {
    prl("- findDirection");
    prl("- x2=" + x2 + ";y2=" + y2 + ";value: " + map[x2][y2]+ ";x3=" + x3 + ";y3="+y3 + ";value: " + map[x3][y3]);
    
    if (map[x2][y2] < map[x3][y3]) {
      return "r";      
    } else if (map[x2][y2] > map[x3][y3]) {      
      return "d";
    } else {
      return findDirection(x2+1,y2, x3, y3+1, steps);
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
class Cell15{
  int x;
  int y;
  int value;
  
  public Cell15(int x, int y, int value) {
    this.x = x;
    this.y = y;
    this.value = value;
  }
  public String toString() {
    return "- cell x=" + x +";y="+y +";value:" + value;
  }
}